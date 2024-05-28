// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class FSharp_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) FSharp_KeywordChoice builtins = new FSharp_KeywordChoice("false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtins.toString())
		{
		case "false":
			interpreter.pushBool(false);
			break;
		case "true":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtins);
		}
	}
}
