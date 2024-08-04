// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class CMD_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CMD_Expression left = new CMD_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMD_PunctuationChoice operator = new CMD_PunctuationChoice("+", "-");
	public @S(30) CMD_Expression right = new CMD_Expression(this, AllowedPrecedence.HIGHER);

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
