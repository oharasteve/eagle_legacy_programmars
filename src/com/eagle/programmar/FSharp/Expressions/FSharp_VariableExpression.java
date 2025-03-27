// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.tokens.PrimaryOperator;

public class FSharp_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) FSharp_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
