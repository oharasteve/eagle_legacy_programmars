// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) SQL_Keyword IF1 = new SQL_Keyword("IF");
	public @S(20) SQL_Expression condition;
	public @S(30) SQL_Keyword THEN = new SQL_Keyword("THEN");
	public @S(40) TokenList<SQL_StatementOrComment> statements;
	public @S(50) @OPT TokenList<SQL_IfElseIfClause> elseIfClauses;
	public @S(60) @OPT SQL_IfElseClause elseClause;
	public @S(70) SQL_Keyword END = new SQL_Keyword("END");
	public @S(80) SQL_Keyword IF2 = new SQL_Keyword("IF");
	public @S(90) PunctuationSemicolon semicolon;

	public static class SQL_IfElseIfClause extends TokenSequence
	{
		public @S(10) SQL_Keyword ELSEIF = new SQL_Keyword("ELSEIF");
		public @S(20) SQL_Expression condition;
		public @S(30) SQL_Keyword THEN = new SQL_Keyword("THEN");
		public @S(40) TokenList<SQL_StatementOrComment> statements;
	}

	public static class SQL_IfElseClause extends TokenSequence
	{
		public @S(10) SQL_Keyword ELSE = new SQL_Keyword("ELSE");
		public @S(20) TokenList<SQL_StatementOrComment> statements;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF1));
			
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (SQL_IfElseIfClause elseIfClause : elseIfClauses._elements)
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
			for (SQL_StatementOrComment stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
		}
		else
		{
			boolean matched = false;
			int seq = 1;
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (SQL_IfElseIfClause elseIfClause : elseIfClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elseIfClause.condition);
					_metrics.get(seq).completedIf(cond2);
					if (cond2)
					{
						for (SQL_StatementOrComment stmt : elseIfClause.statements._elements)
						{
							result = interpreter.tryToInterpret(stmt);
							if (result != Eagle_Statement_Result.NORMAL)
							{
								break;
							}
						}
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
					for (SQL_StatementOrComment stmt : elseClause.statements._elements)
					{
						result = interpreter.tryToInterpret(stmt);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
				}
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

		for (SQL_StatementOrComment thenStatement : statements._elements)
		{
			ArrayList<AbstractStatement> stmts1 = transformer.transformStatement(generator,
					thenStatement.getWhich());
			if (stmts1 != null)
			{
				for (AbstractStatement stmt1 : stmts1)
				{
					ifTrue.add(stmt1);
				}
			}
		}

		if (elseClause != null && elseClause.isPresent())
		{
			for (SQL_StatementOrComment elseStatement : elseClause.statements._elements)
			{
				ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator,
						elseStatement.getWhich());
				if (stmts2 != null)
				{
					for (AbstractStatement stmt2 : stmts2)
					{
						ifFalse.add(stmt2);
					}
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
