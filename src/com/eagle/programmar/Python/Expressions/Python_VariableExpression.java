// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Python_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable variable;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
