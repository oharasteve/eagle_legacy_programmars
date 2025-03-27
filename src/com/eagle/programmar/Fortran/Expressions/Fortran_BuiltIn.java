// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Fortran_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Fortran_KeywordChoice builtinConstant = new Fortran_KeywordChoice(".FALSE.", ".TRUE.");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case ".FALSE.":
			interpreter.pushBool(false);
			return;
		case ".TRUE.":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
	}
}
