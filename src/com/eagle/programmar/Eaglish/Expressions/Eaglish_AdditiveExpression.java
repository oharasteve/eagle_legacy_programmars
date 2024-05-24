// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Eaglish_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("+", "-");
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String oper = operator.getValue();
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (oper)
		{
		case "+":
			interpreter.pushInt(leftValue + rightValue);
			return;
		case "-":
			interpreter.pushInt(leftValue - rightValue);
			return;
		}
		throw new RuntimeException("Unable to handle " + oper + " in Eaglish_AdditiveExpression");
	}
}