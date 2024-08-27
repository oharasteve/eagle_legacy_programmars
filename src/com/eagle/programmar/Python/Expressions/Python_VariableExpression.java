// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Python_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable variable;
	public @S(20) @OPT Python_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			EagleValue value = interpreter.findSymbol(variable.var.getWhich().toString());
			Python_Subscript.evaluateSubscript(interpreter, value, subscript);
			return;
		}
		
		interpreter.tryToInterpret(variable);
	}
}
