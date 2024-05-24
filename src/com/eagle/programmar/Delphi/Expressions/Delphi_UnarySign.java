// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_UnarySign extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
	public @S(20) Delphi_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (sign.toString())
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + sign);
		}
	}
}
