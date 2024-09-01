// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Variable;
import com.eagle.tokens.PrimaryOperator;

public class CMD_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	// Because CMD_Variable is not a TerminalToken, it has to be wrapped in a
	// PrimaryOperator
	public @S(10) CMD_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
