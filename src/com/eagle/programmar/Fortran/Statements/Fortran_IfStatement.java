// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("6j4m0vn9r/index.html") Fortran_Keyword IF1 = new Fortran_Keyword("IF");
	public @S(20) Fortran_Expression condition;
	public @S(30) Fortran_Keyword THEN = new Fortran_Keyword("THEN");
	public @S(40) Fortran_EOLN eoln1;
	public @S(50) TokenList<Fortran_Statement> thenStatements;
	public @S(60) @OPT Fortran_IfElseBlock elseClause;
	public @S(70) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(80) Fortran_Keyword IF2 = new Fortran_Keyword("IF");
	public @S(90) Fortran_EOLN eoln2;

	public static class Fortran_IfElseBlock extends TokenSequence
	{
		public @S(10) Fortran_Keyword ELSE = new Fortran_Keyword("ELSE");
		public @S(20) Fortran_EOLN eoln;
		public @S(30) TokenList<Fortran_Statement> elseStatements;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Fortran_Statement> todo;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF1));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = thenStatements;
		}
		else
		{
			todo = null;

			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatements;
			}
		}

		result = Eagle_Statement_Result.NORMAL;
		if (todo != null)
		{
			for (Fortran_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		for (Fortran_Statement stmt1 : thenStatements._elements)
		{
			ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
			if (stmts2 != null)
			{
				for (AbstractStatement stmt3 : stmts2)
				{
					ifTrue.add(stmt3);
				}
			}
		}

		if (this.elseClause != null && this.elseClause.isPresent())
		{
			for (Fortran_Statement stmt4 : elseClause.elseStatements._elements)
			{
				ArrayList<AbstractStatement> stmts5 = transformer.transformStatement(generator, stmt4.getWhich());
				if (stmts5 != null)
				{
					for (AbstractStatement stmt6 : stmts5)
					{
						ifFalse.add(stmt6);
					}
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
