// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Algol68_KeywordChoice builtinConstant = new Algol68_KeywordChoice("FALSE", "TRUE");
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "FALSE" :
			interpreter.pushBool(false);
			break;
		case "TRUE" :
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than TRUE/FALSE: " + builtinConstant);
		}
	}
}