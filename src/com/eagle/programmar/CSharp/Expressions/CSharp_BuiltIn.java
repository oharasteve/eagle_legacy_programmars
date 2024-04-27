// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_KeywordChoice builtinConstant = new CSharp_KeywordChoice(
			"default", "false", "true", "null", "this", "super");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "false" :
			interpreter.pushBool(false);
			break;
		case "true" :
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
		}
	}
}
