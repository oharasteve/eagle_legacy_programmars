// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class AWK_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("*", "/", "%");
	public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);

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
			if (leftInt % rightInt == 0)
			{
				interpreter.pushInt(leftInt / rightInt);
				return;
			}
			interpreter.pushDouble(leftInt / (double) rightInt);
			return;
		case "%":
			interpreter.pushInt(leftInt % rightInt);
			return;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + oper);
		}
	}
}
