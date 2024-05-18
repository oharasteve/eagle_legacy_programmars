// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Scala_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Scala_Punctuation orOperator = new Scala_Punctuation("||");
	public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);

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
