// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Java_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_Punctuation orOperator = new Java_Punctuation("||");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue = interpreter.getBoolValue(right);
		interpreter.pushBool(leftValue || rightValue);
	}
}
