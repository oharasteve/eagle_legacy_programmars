// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ruby_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("control_expressions_rdoc.html#label-if+Expression") Ruby_Keyword IF = new Ruby_Keyword("if");
	public @S(20) Ruby_Expression condition;
	public @S(30) Ruby_EOLN eoln1;
	public @S(40) TokenList<Ruby_Statement> thenStatements;
	public @S(50) @OPT TokenList<Ruby_IfElsif> ifElsif;
	public @S(60) @OPT Ruby_IfElseClause elseClause;
	public @S(70) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(80) Ruby_EOLN eoln2;

	public static class Ruby_IfElsif extends TokenSequence
	{
		public @S(10) Ruby_Keyword ELSIF = new Ruby_Keyword("elsif");
		public @S(20) Ruby_Expression condition;
		public @S(30) @OPT Ruby_EOLN eoln2;
		public @S(40) TokenList<Ruby_Statement> elseIfStatements;
	}
	
	public static class Ruby_IfElseClause extends TokenSequence
	{
		public @S(10) Ruby_Keyword ELSE = new Ruby_Keyword("else");
		public @S(20) @OPT Ruby_EOLN eoln2;
		public @S(30) TokenList<Ruby_Statement> elseStatements;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Ruby_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			
			if (ifElsif != null && ifElsif.size() > 0)
			{
				for (Ruby_IfElsif elseIfClause : ifElsif._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseIfClause.ELSIF));
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
			if (ifElsif != null && ifElsif.size() > 0)
			{
				for (Ruby_IfElsif elseIfClause : ifElsif._elements)
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
			for (Ruby_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		for (Ruby_Statement stmt : thenStatements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, stmt.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement newStmt : stmts)
				{
					ifTrue.add(newStmt);
				}
			}
		}

		if (elseClause != null && elseClause.isPresent())
		{
			for (Ruby_Statement stmt : elseClause.elseStatements._elements)
			{
				for (AbstractStatement newStmt : transformer.transformStatement(generator, stmt.getWhich()))
				{
					ifFalse.add(newStmt);
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
