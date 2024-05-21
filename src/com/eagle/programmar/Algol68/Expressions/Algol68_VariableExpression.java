// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Algol68_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
