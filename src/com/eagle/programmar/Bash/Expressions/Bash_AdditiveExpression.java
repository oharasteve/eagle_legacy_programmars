// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Bash_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Bash_PunctuationChoice operator = new Bash_PunctuationChoice("+", "-");
	public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+":
			interpreter.pushInt(leftValue + rightValue);
			break;
		case "-":
			interpreter.pushInt(leftValue - rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
