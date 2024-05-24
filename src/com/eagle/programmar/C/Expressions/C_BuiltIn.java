// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class C_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_KeywordChoice builtinConstant = new C_KeywordChoice("false", "true", "NULL", "default");

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
