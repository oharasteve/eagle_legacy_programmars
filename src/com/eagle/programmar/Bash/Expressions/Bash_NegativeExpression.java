// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Bash_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_PunctuationChoice operator = new Bash_PunctuationChoice("-");
	public @S(20) Bash_Expression expr;

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
