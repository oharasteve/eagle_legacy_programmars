// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Python_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_KeywordChoice builtins = new Python_KeywordChoice("None", "False", "True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtins.toString())
		{
		case "False":
			interpreter.pushBool(false);
			break;
		case "True":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtins);
		}
	}
}
