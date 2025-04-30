// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Scala_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("+", "-");
	public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, this, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "+":
				interpreter.pushStr(leftStr + rightStr);
				return;
			default:
				throw new RuntimeException("Unexpected concatenation operator: " + oper);
			}
		}

		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(leftInt + rightInt);
			return;
		case "-":
			interpreter.pushInt(leftInt - rightInt);
			return;
		}

		throw new RuntimeException("Unexpected additive operator: " + oper);
	}
}
