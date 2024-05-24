// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
	public @S(20) CSharp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (operator.toString())
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + operator);
		}
	}
}
