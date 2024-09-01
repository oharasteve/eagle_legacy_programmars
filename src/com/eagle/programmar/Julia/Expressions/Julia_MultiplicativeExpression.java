// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Julia_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("*", "/", "%");
	public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/":
			if (leftValue % rightValue == 0)
			{
				interpreter.pushInt(leftValue / rightValue);
			}
			else
			{
				interpreter.pushDouble(leftValue / (double) rightValue);
			}
			return;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			return;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
}
