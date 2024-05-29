// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Ruby_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ruby_Punctuation orOperator = new Ruby_Punctuation("||");
	public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			// Short circuit, don't bother with RHS
			interpreter.pushBool(true);
		}
		else
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
	}
}
