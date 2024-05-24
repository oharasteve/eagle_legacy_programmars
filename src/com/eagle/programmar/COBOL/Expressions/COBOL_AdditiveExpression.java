// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class COBOL_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) COBOL_PunctuationChoice plusMinus = new COBOL_PunctuationChoice("+", "-");
	public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);

		switch (plusMinus.getValue())
		{
		case "+":
			interpreter.pushInt(leftValue + rightValue);
			break;
		case "-":
			interpreter.pushInt(leftValue - rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + plusMinus + " in COBOL_AdditiveExpression");
		}
	}
}
