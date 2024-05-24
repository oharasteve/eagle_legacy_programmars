// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class AWK_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftInt = interpreter.getIntValue(left);
		int rightInt = interpreter.getIntValue(right);
		switch (operator.getValue())
		{
		case "=":
			interpreter.pushBool(leftInt == rightInt);
			return;
		case "!=":
			interpreter.pushBool(leftInt != rightInt);
			return;
		case "<":
			interpreter.pushBool(leftInt < rightInt);
			return;
		case "<=":
			interpreter.pushBool(leftInt <= rightInt);
			return;
		case ">":
			interpreter.pushBool(leftInt > rightInt);
			return;
		case ">=":
			interpreter.pushBool(leftInt >= rightInt);
			return;
		default:
			throw new RuntimeException("Unable to handle " + operator.getValue() + " with integers");
		}
	}
}
