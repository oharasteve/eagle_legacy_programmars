// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rust_WhileStatement extends TokenSequence implements
		AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("expressions/loop-expr.html#predicate-loops") Rust_Keyword WHILE = new Rust_Keyword("while");
	public @S(20) @NOSPACE Rust_Expression condition;
	public @S(30) @OPT Rust_Comment comment;
	public @S(40) Rust_Statement whileStatement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
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
			result = interpreter.tryToInterpret(whileStatement);
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
