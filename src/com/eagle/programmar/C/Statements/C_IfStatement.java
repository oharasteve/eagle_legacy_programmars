// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_IfStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @DOC("#The-if-Statement") C_Keyword IF = new C_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression condition;
	public @S(40) @OPT C_Comment comment1;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT TokenList<C_Comment> comments;
	public @S(70) C_Statement thenStatement;
	public @S(80) @OPT C_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class C_IfElseClause extends TokenSequence implements AbstractStatement
	{
		public @S(10) @OPT TokenList<C_Comment> comment1;
		public @S(20) C_Keyword ELSE = new C_Keyword("else");
		public @S(30) @OPT TokenList<C_Comment> comment2;
		public @S(40) C_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		C_Statement todo = null;

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

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
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

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
}
