// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class TCL_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_PunctuationChoice operator = new TCL_PunctuationChoice("*", "/", "%");
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/":
			interpreter.pushInt(leftValue / rightValue);
			return;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + operator);
	}
}
