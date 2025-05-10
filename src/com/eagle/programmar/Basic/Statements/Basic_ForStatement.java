// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
import com.eagle.programmar.Basic.Terminals.Basic_EndOfLine;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Basic_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Basic_Keyword FOR = new Basic_Keyword("FOR");
	public @S(20) Basic_Identifier_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Basic_Expression from;
	public @S(50) Basic_Keyword TO = new Basic_Keyword("TO");
	public @S(60) Basic_Expression to;
	public @S(70) @OPT Basic_ForStep step;

	public static class Basic_ForStep extends TokenSequence
	{
		public @S(10) Basic_Keyword STEP = new Basic_Keyword("STEP");
		public @S(20) Basic_Expression step;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		int current = interpreter.getIntValue(from);
		int stop = interpreter.getIntValue(to);
		int by = 1;
		
		if (step != null && step.isPresent())
		{
			by = interpreter.getIntValue(step.step);
		}
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			metric.iterate();
			interpreter.setSymbol(this, var.getValue(), new EagleInteger(current));

			throw new RuntimeException("Need to implement");

//			for (Basic_Statement stmt : actions._elements)
//			{
//				result = interpreter.tryToInterpret(stmt);
//				if (result != Eagle_Statement_Result.NORMAL) break;
//			}
//			
//			if (result == Eagle_Statement_Result.BREAK)
//			{
//				metric.broke();
//				result = Eagle_Statement_Result.NORMAL;
//				break;
//			}
//			else if (result == Eagle_Statement_Result.CONTINUE)
//			{
//				metric.continued();
//				result = Eagle_Statement_Result.NORMAL;
//			}
//			else if (result == Eagle_Statement_Result.RETURN)
//			{
//				break;
//			}
//
//			current += by;
//			if (by < 0)
//			{
//				if (current < stop) break;
//			}
//			else
//			{
//				if (current > stop) break;
//			}
		}
//
//		_metrics.competedLoop(metric);
//		return result;
	}
}
