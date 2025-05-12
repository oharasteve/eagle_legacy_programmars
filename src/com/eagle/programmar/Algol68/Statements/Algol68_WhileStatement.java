// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 11, 2025

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Algol68_WhileStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Algol68_Keyword WHILE = new Algol68_Keyword("WHILE");
	public @S(20) Algol68_Expression condition;
	public @S(30) Algol68_Keyword DO = new Algol68_Keyword("DO");
	public @S(40) TokenList<Algol68_Statement> statements;
	public @S(50) Algol68_Keyword OD = new Algol68_Keyword("OD");

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
			metric.iterate();

			for (Algol68_Statement stmt : statements._elements)
			{
				boolean whileResult = interpreter.getBoolValue(condition);
				if (!whileResult)
				{
					result = Eagle_Statement_Result.BREAK;
					break;
				}

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
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
