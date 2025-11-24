// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DoFunction extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) PunctuationLeftParen leftParen1;
	public @S(20) @DOC("s_do.htm") Lisp_Keyword DO = new Lisp_Keyword("do");
	public @S(30) PunctuationLeftParen leftParen2;
	public @S(40) @OPT Lisp_DoVariables variables;
	public @S(50) PunctuationRightParen rightParen2;
	public @S(60) PunctuationLeftParen leftParen3;
	public @S(70) Lisp_Expression terminateCondition;
	public @S(80) PunctuationRightParen rightParen3;
	public @S(90) TokenList<Lisp_Expression> actions;
	public @S(100) PunctuationRightParen rightParen1;

	public static class Lisp_DoVariables extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Lisp_Variable_Definition var;
		public @S(30) Lisp_Expression initialValue;
		public @S(40) @OPT Lisp_Expression increment;
		public @S(50) PunctuationRightParen rightParen;
	}

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, DO);
		}
		ForLoopMetric metric = new ForLoopMetric();

		EagleValue val = null;
		if (variables != null && variables.isPresent())
		{
			val = interpreter.getEagleValue(variables.initialValue);
			interpreter.setSymbol(variables.var, variables.var.getValue(), val);
		}

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean done = interpreter.getBoolValue(terminateCondition);
			if (done) break;

			metric.iterate();

			for (Lisp_Expression action : actions._elements)
			{
				result = interpreter.tryToInterpret(action);

				if (result == Eagle_Statement_Result.RETURN)
				{
					metric.broke();
				}

				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			if (result != Eagle_Statement_Result.NORMAL) break;

			if (variables.initialValue != null && variables.increment != null)
			{
				val = interpreter.getEagleValue(variables.increment);
				interpreter.setSymbol(variables.increment, variables.var.getValue(), val);
			}
		}

		boolean backwards = false; // TODO -- this might be true in some cases
		_metrics.competedLoop(metric, backwards);

		if (variables != null && variables.var != null)
		{
			interpreter.removeSymbol(variables.var.getValue());
		}

		return Eagle_Statement_Result.NORMAL;
	}
}
