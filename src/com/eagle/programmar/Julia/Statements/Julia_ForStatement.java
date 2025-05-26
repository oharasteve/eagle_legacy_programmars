// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Expressions.Julia_RangeExpression;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Julia_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("manual/control-flow/#man-loops") Julia_Keyword FOR = new Julia_Keyword("for");
	public @S(20) Julia_Variable var;
	public @S(30) Julia_Keyword IN = new Julia_Keyword("in");
	public @S(40) Julia_Expression values;
	public @S(50) Julia_EOLN eoln1;
	public @S(60) TokenList<Julia_Statement> statements;
	public @S(70) Julia_Keyword END = new Julia_Keyword("end");
	public @S(80) Julia_EOLN eoln2;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (!(values.getWhich() instanceof Julia_RangeExpression))
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}
		
		Julia_RangeExpression range = (Julia_RangeExpression) values.getWhich();
		int start = interpreter.getIntValue(range.first);
		int stop = interpreter.getIntValue(range.lastOrIncrement);
		int incr = 1;
		if (range.hasIncr != null && range.hasIncr.isPresent())
		{
			incr = stop;
			stop = interpreter.getIntValue(range.hasIncr.last);
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (incr > 0 && i > stop) break;
			if (incr < 0 && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

			for (Julia_Statement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

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

			i += incr;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
