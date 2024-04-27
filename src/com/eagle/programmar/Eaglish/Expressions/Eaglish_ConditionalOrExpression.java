// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Eaglish_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_KeywordChoice orOperator = new Eaglish_KeywordChoice("OR", "XOR");
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		String oper = orOperator.getValue();
		switch (oper)
		{
		case "OR":
			if (leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(true);
				return;
			}
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(rightVal);
			return;
		case "XOR":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in Eaglish_ConditionalOrExpression");
		}
	}
}