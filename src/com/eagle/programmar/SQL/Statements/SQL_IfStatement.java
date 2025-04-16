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
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_IfStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) SQL_Keyword IF1 = new SQL_Keyword("IF");
	public @S(20) SQL_Expression condition;
	public @S(30) SQL_Keyword THEN = new SQL_Keyword("THEN");
	public @S(40) TokenList<SQL_StatementOrComment> statements;
	public @S(50) @OPT SQL_IfElseClause elseClause;
	public @S(60) SQL_Keyword END = new SQL_Keyword("END");
	public @S(70) SQL_Keyword IF2 = new SQL_Keyword("IF");
	public @S(80) PunctuationSemicolon semicolon;
	
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
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause));
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
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
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

		return result;
	}
}
