// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Element;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("TclCmd/for.html") TCL_Keyword FOR = new TCL_Keyword("for");
	public @S(20) PunctuationLeftBrace leftBrace1;
	public @S(30) TCL_Element initialize;
	public @S(40) PunctuationRightBrace rightBrace1;
	public @S(50) PunctuationLeftBrace leftBrace2;
	public @S(60) TCL_Expression condition;
	public @S(70) PunctuationRightBrace rightBrace2;
	public @S(80) PunctuationLeftBrace leftBrace3;
	public @S(90) TCL_Element increment;
	public @S(100) PunctuationRightBrace rightBrace3;
	public @S(110) TCL_Element action;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(initialize);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();

			result = interpreter.tryToInterpret(action);

			if (result == Eagle_Statement_Result.BREAK)
			{
				metric.broke();
				result = Eagle_Statement_Result.NORMAL;
				break;
			}
			else if (result == Eagle_Statement_Result.CONTINUE)
			{
				metric.continued();
				result = Eagle_Statement_Result.NORMAL;
			}
			else if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}

			interpreter.tryToInterpret(increment);
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
