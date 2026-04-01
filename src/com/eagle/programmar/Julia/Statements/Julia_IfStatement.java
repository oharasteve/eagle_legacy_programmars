// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Julia_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("manual/control-flow/#man-conditional-evaluation") Julia_Keyword IF = new Julia_Keyword("if");
	public @S(20) Julia_Expression condition;
	public @S(30) Julia_EOLN eoln1;
	public @S(40) TokenList<Julia_Statement> thenStatements;
	public @S(50) @OPT TokenList<Julia_ElseIfClause> elseIfClauses;
	public @S(60) @OPT Julia_IfElseClause elseClause;
	public @S(70) Julia_Keyword END = new Julia_Keyword("end");
	public @S(80) Julia_EOLN eoln2;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Julia_IfElseClause extends TokenSequence
	{
		public @S(10) Julia_Keyword ELSE = new Julia_Keyword("else");
		public @S(20) @OPT Julia_EOLN eoln2;
		public @S(30) TokenList<Julia_Statement> elseStatements;
	}

	public static class Julia_ElseIfClause extends TokenSequence
	{
		public @S(10) Julia_Keyword ELSEIF = new Julia_Keyword("elseif");
		public @S(20) Julia_Expression condition;
		public @S(30) @OPT Julia_EOLN eoln2;
		public @S(40) TokenList<Julia_Statement> elseIfStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Julia_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (Julia_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseIfClause.ELSEIF));
				}
			}
			
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatements;
		}
		else
		{
			boolean matched = false;
			int seq = 1;
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (Julia_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elseIfClause.condition);
					_metrics.get(seq).completedIf(cond2);
					if (cond2)
					{
						todo = elseIfClause.elseIfStatements;
						matched = true;
						break;
					}
					seq++;
				}
			}
			
			if (!matched)
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = elseClause.elseStatements;
				}
			}
		}

		if (todo != null)
		{
			for (Julia_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		for (Julia_Statement stmt1 : thenStatements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, stmt1.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt2 : stmts)
				{
					ifTrue.add(stmt2);
				}
			}
		}

		if (this.elseClause != null && this.elseClause.isPresent())
		{
			for (Julia_Statement stmt3 : elseClause.elseStatements._elements)
			{
				for (AbstractStatement stmt4 : transformer.transformStatement(generator, stmt3.getWhich()))
				{
					ifFalse.add(stmt4);
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
