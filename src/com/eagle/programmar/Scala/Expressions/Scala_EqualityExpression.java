// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Scala_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("==", "!=");
	public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "==":
			interpreter.pushBool(leftValue == rightValue);
			return;
		case "!=":
			interpreter.pushBool(leftValue != rightValue);
			return;
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
}
