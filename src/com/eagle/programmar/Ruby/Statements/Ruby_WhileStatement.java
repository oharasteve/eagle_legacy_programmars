// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2025

package com.eagle.programmar.Ruby.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Expressions.Ruby_ParenthesizedExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_Subfield;
import com.eagle.programmar.Ruby.Functions.Ruby_FunctionCall;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Ruby_WhileStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Ruby_Keyword WHILE = new Ruby_Keyword("while");
	public @S(20) Ruby_Expression condition;
	public @S(30) @OPT Ruby_Keyword DO = new Ruby_Keyword("do");
	public @S(40) Ruby_EOLN eoln1;
	public @S(50) TokenList<Ruby_Statement> statements;
	public @S(60) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(70) Ruby_EOLN eoln2;

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

			for (Ruby_Statement statement : statements._elements)
			{
				result = interpreter.tryToInterpret(statement);
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
