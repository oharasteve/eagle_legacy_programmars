// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class VB_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) VB_KeywordChoice builtIns = new VB_KeywordChoice("false", "true", "nothing");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String builtIn = builtIns.getValue();
		switch (builtIn)
		{
		case "true" :
			interpreter.pushBool(true);
			break;
		case "false" :
			interpreter.pushBool(false);
			break;
		default:
			throw new RuntimeException("Unable to handle " + builtIn);
		}
	}
}
