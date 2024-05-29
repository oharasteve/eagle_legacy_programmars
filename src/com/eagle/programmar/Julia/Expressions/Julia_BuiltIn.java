// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Terminals.Julia_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Julia_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Julia_KeywordChoice builtinConstant = new Julia_KeywordChoice("false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "false":
			interpreter.pushBool(false);
			break;
		case "true":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
		}
	}
}
