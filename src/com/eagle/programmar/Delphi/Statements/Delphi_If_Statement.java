// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Delphi_If_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#If_Statements") Delphi_Keyword IF = new Delphi_Keyword(
			"If");
	public @S(20) Delphi_Expression condition;
	public @S(30) @OPT TokenList<Delphi_Comment> comments1;
	public @S(40) Delphi_Keyword THEN = new Delphi_Keyword("Then");
	public @S(50) @OPT TokenList<Delphi_Comment> comments2;
	public @S(60) @OPT Delphi_Statement thenStmt;
	public @S(70) @OPT Delphi_If_Else ifElse;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Delphi_If_Else extends TokenSequence
	{
		public @S(10) @OPT TokenList<Delphi_Comment> comments;
		public @S(20) Delphi_Keyword ELSE = new Delphi_Keyword("Else");
		public @S(30) Delphi_Statement elseStmt;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Delphi_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			if (ifElse != null && ifElse.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, ifElse));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStmt;
		}
		else
		{
			// Check for 'else'
			if (ifElse != null && ifElse.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = ifElse.elseStmt;
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
}
