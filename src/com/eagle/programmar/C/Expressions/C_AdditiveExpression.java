// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class C_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
	public @S(30) C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		int rightSide = interpreter.getIntValue(right);
		if (leftValue.isString())
		{
			String leftSide = leftValue.forceStringValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushStr(leftSide.substring(rightSide));
				break;
			default:
				throw new RuntimeException("Unexpected string additive operator: " + operator);
			}
		}
		else
		{
			int leftSide = leftValue.forceIntegerValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushInt(leftSide + rightSide);
				break;
			case "-":
				interpreter.pushInt(leftSide - rightSide);
				break;
			default:
				throw new RuntimeException("Unexpected numeric additive operator: " + operator);
			}
		}
	}
}
