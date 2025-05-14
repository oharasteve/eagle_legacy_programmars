// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("js_if_else.asp") Javascript_Keyword IF = new Javascript_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Javascript_Expression, PunctuationComma> conditions;
	public @S(40) @OPT TokenList<Javascript_Comment> comment1;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT TokenList<Javascript_Comment> comments2;
	public @S(70) Javascript_Element thenStatement;
	public @S(80) @OPT TokenList<Javascript_Comment> comments3;
	public @S(90) @OPT Javascript_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Javascript_IfElseClause extends TokenSequence
	{
		public @S(10) Javascript_Keyword ELSE = new Javascript_Keyword("else");
		public @S(20) @OPT Javascript_Comment comment;
		public @S(30) Javascript_Element elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Javascript_Element todo = null;

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

		Javascript_Expression condition = conditions.first();
		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatement;
		}
		else
		{
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatement;
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
}
