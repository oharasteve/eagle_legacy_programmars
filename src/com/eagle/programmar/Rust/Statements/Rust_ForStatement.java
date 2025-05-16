// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleRange;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rust_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("expressions/loop-expr.html#iterator-loops") Rust_Keyword FOR = new Rust_Keyword("for");
	public @S(20) Rust_Variable var;
	public @S(30) Rust_Keyword IN = new Rust_Keyword("in");
	public @S(40) Rust_Expression expr;
	public @S(50) Rust_Statement stmt;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleRange range = interpreter.getRangeValue(expr);
		int start = range._lowValue;
		int stop = range._highValue;
		int step = range._step;

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		int i = start;
		boolean backwards = false;
		if (step < 0)
		{
			backwards = true;
			i = stop;
		}

		while (true)
		{
			if (backwards && i <= start) break;
			if (! backwards && i >= stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.var.toString(), new EagleInteger(i));

			result = interpreter.tryToInterpret(stmt);

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

			i += step; // Might be negative
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
