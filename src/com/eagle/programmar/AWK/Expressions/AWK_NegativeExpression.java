// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class AWK_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("-");
	public @S(20) AWK_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (operator.toString())
		{
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + operator);
		}
	}
}
