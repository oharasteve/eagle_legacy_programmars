// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Julia_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Julia_Punctuation orOperator = new Julia_Punctuation("||");
	public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);

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
