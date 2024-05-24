// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Python_Multiplicative_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			break;
		case "//":
			interpreter.pushInt(leftValue / rightValue);
			break;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
}
