// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class C_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) C_Expression leftEq = new C_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
	public @S(30) C_Expression rightEq = new C_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(leftEq);
		int rightValue = interpreter.getIntValue(rightEq);
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