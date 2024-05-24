// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("+", "-");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+":
			interpreter.pushInt(leftValue + rightValue);
			return;
		case "-":
			interpreter.pushInt(leftValue - rightValue);
			return;
		}
		throw new RuntimeException("Unexpected additive operator: " + operator);
	}
}
