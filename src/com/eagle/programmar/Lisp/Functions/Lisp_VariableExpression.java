// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Lisp_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Lisp_Variable var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(var);
	}
}
