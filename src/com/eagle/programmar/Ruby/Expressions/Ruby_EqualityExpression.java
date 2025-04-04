// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Ruby_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("==", "!=");
	public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.getValue();

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			}
		}

		if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDbl = leftValue.forceDoubleValue();
			double rightDbl = rightValue.forceDoubleValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftDbl == rightDbl);
				return;
			case "!=":
				interpreter.pushBool(leftDbl != rightDbl);
				return;
			}
		}

		if (leftValue.isInteger() || rightValue.isInteger())
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}

		if (leftValue.isBoolean() && rightValue.isBoolean())
		{
			boolean leftBool = leftValue.forceBooleanValue();
			boolean rightBool = rightValue.forceBooleanValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftBool == rightBool);
				return;
			case "!=":
				interpreter.pushBool(leftBool != rightBool);
				return;
			}
		}

		throw new RuntimeException("Unable to handle " + oper);
	}
}
