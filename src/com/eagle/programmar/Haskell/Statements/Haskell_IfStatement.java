// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_Statement;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Haskell_IfStatement extends TokenSequence
		implements EagleRunnableWithResult
{
	public @S(10) Haskell_Keyword IF = new Haskell_Keyword("if");
	public @S(20) Haskell_Expression condition;
	public @S(30) Haskell_EndOfLine eoln;
	public @S(40) Haskell_Keyword THEN = new Haskell_Keyword("then");
	public @S(50) Haskell_Statement thenStatement;
	public @S(60) Haskell_Keyword ELSE = new Haskell_Keyword("else");
	public @S(70) Haskell_Statement elseStatement;
	
	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Haskell_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			_metrics.add(new IfCondMetrics(interpreter._metrics, ELSE));
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatement;
		}
		else
		{
			_metrics.get(1).completedIf(true);
			todo = elseStatement;
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
}
