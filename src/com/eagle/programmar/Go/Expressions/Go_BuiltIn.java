// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Go_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Go_KeywordChoice builtinConstant = new Go_KeywordChoice("false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "false":
			interpreter.pushBool(false);
			return;
		case "true":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
	}
}
