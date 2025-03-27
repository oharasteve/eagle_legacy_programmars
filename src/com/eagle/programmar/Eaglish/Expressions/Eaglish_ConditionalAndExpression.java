// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Eaglish_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_Keyword andOperator = new Eaglish_Keyword("AND");
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		String oper = andOperator.getValue();
		switch (oper)
		{
		case "AND":
			if (!leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(false);
				return;
			}
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in Eaglish_ConditionalAndExpression");
		}
	}
}