// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Procedure;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.PLI_Subscript.PLI_ExpressionOrStar;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;

public class PLI_VariableOrFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PLI_Identifier_Reference id;
	public @S(20) @OPT PLI_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			String name = id.getValue();
			int argCount = subscript.args.getPrimaryCount();
			
			// First: look through built-in functions
			switch (name.toUpperCase())
			{
			case "MOD":
				if (argCount != 2)
				{
					throw new RuntimeException("MOD function requires 2 arguments");
				}
				int numer = interpreter.getIntValue(subscript.args.getPrimaryElement(0));
				int denom = interpreter.getIntValue(subscript.args.getPrimaryElement(1));
				interpreter.pushInt(numer % denom);
				return;
			case "TRUNC":
				if (argCount != 1)
				{
					throw new RuntimeException("TRUNC function requires 1 argument");
				}
				double x = interpreter.getDoubleValue(subscript.args.getPrimaryElement(0));
				interpreter.pushInt((int) x);
				return;
			}
			
			// Next: search user variables
			EagleValue var = interpreter.findSymbol(name);
			if (var != null && var.isArray() && argCount == 1)
			{
				EagleArray array = (EagleArray) var;
				int subscr = interpreter.getIntValue(subscript.args.getPrimaryElement(0));
				EagleValue val = array.getValue(subscr);
				interpreter.pushEagleValue(val);
				return;
			}

			// Next: search for the Procedure definition
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a Procedure named " + name);
			}
			PLI_Procedure proc = (PLI_Procedure) fn;
			if (interpreter._TRACE) System.err.println("*** Calling " + name);

			// Count the parameters
			int paramCount = proc.params.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + name + ", expected params = " + paramCount + ", but actual args = " + argCount);
			}

			// Assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				PLI_Identifier_Reference param = proc.params.params.getPrimaryElement(i);
				PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
				PLI_Expression expr = (PLI_Expression) arg.getWhich();
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
			}

			// Evaluate the function
			long startTime = System.nanoTime();
			for (PLI_StatementOrComment stmt : proc.statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
			long elapsedTime = System.nanoTime() - startTime;
			if (proc._metrics == null)
			{
				proc._metrics = new CallMetrics(interpreter._metrics, name, proc);
			}
			proc._metrics.addCallFrom(this, elapsedTime);

			// Remove all the parameters
			for (int i = 0; i < argCount; i++)
			{
				PLI_Identifier_Reference param = proc.params.params.getPrimaryElement(i);
				interpreter.removeSymbols(param.getValue());
			}
		}
		else
		{
			// Just a variable
			EagleValue value = interpreter.findSymbol(id.toString());
			interpreter.pushEagleValue(value);
		}
	}
}
