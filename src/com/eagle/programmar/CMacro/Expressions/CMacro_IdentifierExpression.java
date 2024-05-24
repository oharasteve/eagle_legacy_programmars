// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMacro.CMacroFunctionParens;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;

public class CMacro_IdentifierExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CMacro_Identifier_Reference identifier;
	public @S(20) @OPT CMacroFunctionParens params;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(identifier);
	}
}