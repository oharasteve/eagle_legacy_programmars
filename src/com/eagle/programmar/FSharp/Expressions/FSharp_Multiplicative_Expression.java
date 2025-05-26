// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class FSharp_Multiplicative_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) FSharp_PunctuationChoice operator = new FSharp_PunctuationChoice("*", "/", "%");
	public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "*":
			interpreter.pushInt(leftInt * rightInt);
			return;
		case "/":
			interpreter.pushInt(leftInt / rightInt);
			return;
		case "%":
			interpreter.pushInt(leftInt % rightInt);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + oper);
	}
}
