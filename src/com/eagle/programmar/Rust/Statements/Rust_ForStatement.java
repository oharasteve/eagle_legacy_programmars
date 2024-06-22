// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;

public class Rust_ForStatement extends TokenSequence implements EagleRunnableWithResult
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
		int start = 1; // interpreter.getIntValue(startValue);
		int stop = 2; // interpreter.getIntValue(stopValue);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = start; i <= stop; i++)
		{
			metric.iterate();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.toString(), new EagleInteger(i));
			
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
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
