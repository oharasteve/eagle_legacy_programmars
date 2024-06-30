// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams.Ada_Parameter;
import com.eagle.programmar.Ada.Statements.Ada_FunctionCall.Ada_FunctionArguments;
import com.eagle.programmar.Ada.Statements.Ada_FunctionCall.Ada_FunctionArguments.Ada_FunctionArg;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;

public class Ada_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_Variable methodName;
	public @S(20) @OPT Ada_Punctuation question = new Ada_Punctuation("?");
	public @S(30) Ada_FunctionArguments argList;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = methodName.vars.first();
		if (interpreter._TRACE) System.err.println("*** Calling " + id + "()");

		// Have to search for the FUNCTION definition
		Ada_Function func = null;
		for (AbstractFunction absFn : interpreter._functionList)
		{
			Ada_Function fn = (Ada_Function) absFn;
			if (fn.id.getValue().equalsIgnoreCase(id.getValue()))
			{
				// Found it!
				func = fn;
				break;
			}
		}

		if (func == null)
		{
			throw new RuntimeException("Unable to find a Function named " + id);
		}

		// Doesn't do much, just set metrics
		interpreter.tryToInterpret(func);
		
		// Make sure the function args match up
		int argCount = 0;
		if (argList.isPresent())
		{
			argCount = argList.arguments.getPrimaryCount();
			int paramCount = func.params.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + id.getValue() + " expects #args = " + paramCount + ", but was given " + argCount);
			}
	
			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Ada_FunctionArg arg = argList.arguments.getPrimaryElement(i);
				Ada_Parameter param = func.params.parameters.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(arg.expr);
				interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						param.param.getValue(), val);
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		for (Ada_Statement stmt : func.stmts2._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		if (argList.isPresent())
		{
			// Now remove all those parameters
			for (int i = 0; i < argCount; i++)
			{
				Ada_Parameter param = func.params.parameters.getPrimaryElement(i);
				interpreter._symbolTable.removeSymbols(param.param.getValue());
			}
		}
	}
}
