// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	// Because Powershell_Variable is not a TerminalToken, it has to be wrapped in a
	// PrimaryOperator
	public @S(10) Powershell_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
