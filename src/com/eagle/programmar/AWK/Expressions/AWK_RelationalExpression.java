// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class AWK_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftVal = interpreter.getEagleValue(left);
		EagleValue rightVal = interpreter.getEagleValue(right);

		if (leftVal.isString() || rightVal.isString())
		{
			String leftStr = leftVal.forceStringValue();
			String rightStr = rightVal.forceStringValue();
			switch (operator.getValue())
			{
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			}
		}

		if (leftVal.isDouble() || rightVal.isDouble())
		{
			double leftDbl = leftVal.forceDoubleValue();
			double rightDbl = rightVal.forceDoubleValue();
			switch (operator.getValue())
			{
			case "==":
				interpreter.pushBool(leftDbl == rightDbl);
				return;
			case "!=":
				interpreter.pushBool(leftDbl != rightDbl);
				return;
			case "<":
				interpreter.pushBool(leftDbl < rightDbl);
				return;
			case "<=":
				interpreter.pushBool(leftDbl <= rightDbl);
				return;
			case ">":
				interpreter.pushBool(leftDbl > rightDbl);
				return;
			case ">=":
				interpreter.pushBool(leftDbl >= rightDbl);
				return;
			}
		}

		if (leftVal.isInteger() || rightVal.isInteger())
		{
			int leftInt = leftVal.forceIntegerValue();
			int rightInt = rightVal.forceIntegerValue();
			switch (operator.getValue())
			{
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}

		throw new RuntimeException("Can only handle ints and strings");
	}
}
