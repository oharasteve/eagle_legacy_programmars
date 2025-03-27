// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Eaglish_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
