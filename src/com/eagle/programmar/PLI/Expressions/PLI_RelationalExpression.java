// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class PLI_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PLI_PunctuationChoice operator = new PLI_PunctuationChoice(
			"^>", "^<", "^=", "<=", ">=", ">", "<", "=");
	public @S(30) PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);

		if (leftValue.isString() && rightValue.isString())
		{
			String leftStr = interpreter.getStrValue(left);
			String rightStr = interpreter.getStrValue(right);
			switch (operator.toString())
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "^=":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			default:
				throw new RuntimeException("Unable to handle " + operator + " with strings");
			}
		}
		else if (leftValue.isInteger() && rightValue.isInteger())
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			switch (operator.toString())
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "^=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=", "^>":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=", "^<":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
}
