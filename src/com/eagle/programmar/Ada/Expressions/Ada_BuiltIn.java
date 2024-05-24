// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Ada_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_KeywordChoice builtinConstant = new Ada_KeywordChoice("false", "true");

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