// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Eaglish_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("-", "+");
	public @S(20) Eaglish_Expression expr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = interpreter.getIntValue(expr);
		String oper = operator.getValue();
		switch (oper)
		{
		case "-" :
			interpreter.pushInt(-value);
			return;
		case "+" :
			interpreter.pushInt(value);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in Eaglish_NegativeExpression");	
		}
	}
}