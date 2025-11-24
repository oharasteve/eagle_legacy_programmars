// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Basic_AdditiveExpression extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Basic_Expression left = new Basic_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Basic_PunctuationChoice operator = new Basic_PunctuationChoice("+", "-");
	public @S(30) Basic_Expression right = new Basic_Expression(this, AllowedPrecedence.HIGHER);

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

		if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDouble = leftValue.forceDoubleValue();
			double rightDouble = rightValue.forceDoubleValue();
			switch (oper)
			{
			case "+":
				interpreter.pushDouble(leftDouble + rightDouble);
				return;
			case "-":
				interpreter.pushDouble(leftDouble - rightDouble);
				return;
			}
		}
		else
		{
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
		}
		throw new RuntimeException("Unable to handle " + oper);
	}
}
