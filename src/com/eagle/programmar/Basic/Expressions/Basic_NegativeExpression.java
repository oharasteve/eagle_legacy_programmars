// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Basic_NegativeExpression extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Basic_Punctuation operator = new Basic_Punctuation("-");
	public @S(20) Basic_Expression expr;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		String oper = operator.getValue();
		
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(value.typeName());

		int val = value.forceIntegerValue();
		interpreter.pushInt(-val);
	}
}
