// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_IfStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Ada_Keyword IF = new Ada_Keyword("if");
	public @S(20) Ada_Expression condition;
	public @S(30) Ada_Keyword THEN = new Ada_Keyword("then");
	public @S(40) TokenList<Ada_Statement> thenStatements;
	public @S(50) @OPT Ada_IfElseClause elseClause;
	public @S(60) Ada_Keyword END = new Ada_Keyword("end");
	public @S(70) Ada_Keyword IF2 = new Ada_Keyword("if");
	public @S(80) PunctuationSemicolon semicolon;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Ada_IfElseClause extends TokenSequence
	{
		public @S(10) Ada_Keyword ELSE = new Ada_Keyword("else");
		public @S(20) TokenList<Ada_Statement> elseStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Ada_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
			if (elseClause.isPresent())
			{
				_metrics.add(
						new IfCondMetrics(interpreter._metrics, elseClause.getFileName(), elseClause.getStartLine(), elseClause.getStartChar()));
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
			// Check for 'else'
			if (elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatements;
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			for (Ada_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}
}
