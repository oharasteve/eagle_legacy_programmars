// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Perl_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Perl_KeywordChoice builtIn = new Perl_KeywordChoice("FALSE", "False", "false", "TRUE", "True", "true",
			"NULL", "null", "T_CLASS", "T_FUNCTION", "T_INCLUDE", "T_INCLUDE_ONCE", "T_REQUIRE", "T_REQUIRE_ONCE",
			"T_USE", "namespace");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtIn.toString().toLowerCase())
		{
		case "false":
			interpreter.pushBool(false);
			return;
		case "true":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtIn);
	}
}
