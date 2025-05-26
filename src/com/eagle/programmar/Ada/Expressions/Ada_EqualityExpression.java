// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Ada_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ada_PunctuationChoice operator = new Ada_PunctuationChoice("=", "/=");
	public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);

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

		if (leftValue.isString() || rightValue.isString())
		{
			String leftString = leftValue.forceStringValue();
			String rightString = rightValue.forceStringValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftString.equals(rightString));
				return;
			case "/=":
				interpreter.pushBool(!leftString.equals(rightString));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "/=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected equality operator: " + oper);
	}
}