// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Algol68_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("+", "-");
	public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+" :
			interpreter.pushInt(leftValue + rightValue);
			break;
		case "-" :
			interpreter.pushInt(leftValue - rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
