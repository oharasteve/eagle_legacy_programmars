// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_SingleOrMultiLineStatement;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class FSharp_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("loops-for-to-expression") FSharp_Keyword FOR = new FSharp_Keyword("for");
	public @S(20) FSharp_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) FSharp_Expression startValue;
	public @S(50) FSharp_KeywordChoice TO = new FSharp_KeywordChoice("to", "downto");
	public @S(60) FSharp_Expression stopValue;
	public @S(70) FSharp_Keyword DO = new FSharp_Keyword("do");
	public @S(80) FSharp_SingleOrMultiLineStatement forActions;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int start = interpreter.getIntValue(startValue);
		int stop = interpreter.getIntValue(stopValue);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		String which = TO.getValue();
		boolean backwards = which.equals("downto");

		int i = start;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < stop) break;

			metric.iterate();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.id.getValue(), new EagleInteger(i));

			result = interpreter.tryToInterpret(forActions);

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

			if (backwards)
				i--;
			else
				i++;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
