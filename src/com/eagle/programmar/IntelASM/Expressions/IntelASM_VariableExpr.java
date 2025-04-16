// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2025

package com.eagle.programmar.IntelASM.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;

public class IntelASM_VariableExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) IntelASM_Identifier_Reference id;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(id);
	}
}
