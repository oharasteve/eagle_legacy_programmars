// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Go_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Go_Punctuation orOperator = new Go_Punctuation("||");
	public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);

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
