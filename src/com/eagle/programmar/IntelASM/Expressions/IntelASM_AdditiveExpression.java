// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

package com.eagle.programmar.IntelASM.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class IntelASM_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) IntelASM_Expression left = new IntelASM_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) IntelASM_PunctuationChoice operator = new IntelASM_PunctuationChoice("+", "-");
	public @S(30) IntelASM_Expression right = new IntelASM_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftInt = interpreter.getIntValue(left);
		int rightInt = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+":
			// System.out.println("****** " + leftInt + " + " + rightInt + " ******");
			interpreter.pushInt(leftInt + rightInt);
			break;
		case "-":
			// System.out.println("****** " + leftInt + " - " + rightInt + " ******");
			interpreter.pushInt(leftInt - rightInt);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
