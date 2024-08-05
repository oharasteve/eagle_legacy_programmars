// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Procedure;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Reference;
import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class TCL_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) TCL_Function_Reference function;
	public @S(20) TokenList<TCL_Expression> values;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = function.getValue();
		
		// Look up the function
		AbstractFunction fn = interpreter._functionList.get(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a procedure named " + name);
		}
		TCL_Procedure proc = (TCL_Procedure) fn;

		// Make sure the function args match up
		int argCount = values.size();
		int paramCount = proc.vars.size();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Procedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			TCL_Expression expr = values._elements.get(i);
			TCL_Variable_Definition param = proc.vars._elements.get(i);

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.getValue(), val);
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		interpreter.tryToInterpret(proc.block);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		proc._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			TCL_Variable_Definition param = proc.vars._elements.get(i);
			interpreter._symbolTable.removeSymbols(param.getValue());
		}
	}
}
