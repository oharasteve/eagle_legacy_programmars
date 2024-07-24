// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class FSharp_Additive_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) FSharp_PunctuationChoice operator = new FSharp_PunctuationChoice("+", "-");
	public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushStr(leftStr + rightStr);
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushInt(leftInt + rightInt);
				return;
			case "-":
				interpreter.pushInt(leftInt - rightInt);
				return;
			}
		}
			
		throw new RuntimeException("Unexpected additive operator: " + operator);
	}
}
