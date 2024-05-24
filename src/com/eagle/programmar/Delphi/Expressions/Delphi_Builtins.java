// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Builtins extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice("False", "True", "Nil");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "False":
			interpreter.pushBool(false);
			break;
		case "True":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than True/False: " + builtinConstant);
		}
	}
}
