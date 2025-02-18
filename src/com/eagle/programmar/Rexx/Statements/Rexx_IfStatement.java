// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Statement;
import com.eagle.programmar.Rexx.Rexx_Statement.Rexx_BaseStatement;
import com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rexx_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("752y8abs.aspx") Rexx_Keyword IF = new Rexx_Keyword("IF");
	public @S(20) Rexx_Expression condition;
	public @S(30) Rexx_Keyword THEN = new Rexx_Keyword("THEN");
	public @S(40) Rexx_EndOfLine eoln;
	public @S(50) Rexx_BaseStatement thenStatement;
	public @S(60) @OPT Rexx_IfElseClause elseClause;

	public static class Rexx_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<Rexx_Comment> comments;
		public @S(20) Rexx_EndOfLine eoln1;
		public @S(30) Rexx_Keyword ELSE = new Rexx_Keyword("ELSE");
		public @S(40) Rexx_EndOfLine eoln2;
		public @S(50) Rexx_BaseStatement elseStatement;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
		}
	
		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		if (cond)
		{
			result = interpreter.tryToInterpret(thenStatement);
		}
		else if (elseClause != null)
		{
			result = interpreter.tryToInterpret(elseClause.elseStatement);
		}
		return result;
	}
}
