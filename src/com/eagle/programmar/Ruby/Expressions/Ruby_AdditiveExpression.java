// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Ruby_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("+", "-");
	public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);

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
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
