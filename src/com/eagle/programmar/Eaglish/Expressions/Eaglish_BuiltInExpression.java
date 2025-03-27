// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 25, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Eaglish_BuiltInExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_KeywordChoice builtIns = new Eaglish_KeywordChoice("TRUE", "FALSE");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String builtIn = builtIns.getValue();
		switch (builtIn)
		{
		case "TRUE":
			interpreter.pushBool(true);
			break;
		case "FALSE":
			interpreter.pushBool(false);
			break;
		default:
			throw new RuntimeException("Unable to handle " + builtIn);
		}
	}
}
