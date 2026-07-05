// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import java.util.ArrayList;

import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Bash_Element;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_FunctionCall extends TokenSequence implements EagleRunnable
{
	public @S(10) Bash_WhatFunction what;
	public @S(20) @OPT TokenList<Bash_FunctionArg> args;

	public static class Bash_WhatFunction extends TokenChooser
	{
		public @CHOICE Bash_Filename XXfilName;
		public @CHOICE Bash_Variable XXvariable;
	}

	public static class Bash_FunctionArg extends TokenChooser
	{
		public @CHOICE Bash_Literal XXliteral;
		public @CHOICE Bash_Number XXnumber;
		public @CHOICE Bash_Filename XXfileName;
		public @CHOICE Bash_Variable XXvariable;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (what.getWhich() instanceof Bash_Variable)
		{
			Bash_Variable var = (Bash_Variable) what.getWhich();
			String name = var.id.getValue();

			// Have to search for the FUNCTION definition
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a function named " + name);
			}
			Bash_Function_Explicit func = (Bash_Function_Explicit) fn;
			AbstractFunction saveFunc = interpreter.getCurrentFunction(); // Often null
			interpreter.setCurrentFunction(func); // Place to save exist code and string outputs
			func._exitStatus = 0;
			func._echoOutputs = null;

			int argCount = args.size();

			ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Bash_FunctionArg arg = args._elements.get(i);
				String paramName = "$" + (i + 1);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(this, paramName, val);
				argTypes.add(val.getType());
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function
			interpreter.callingFunction(name, func);
			for (Bash_Element stmt : func.statements._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func);

			EagleInteger code = new EagleInteger(func._exitStatus);
			interpreter.setSymbol(this, "$?", code);

			interpreter.setCurrentFunction(saveFunc); // Restore previous value
			if (func._echoOutputs != null)
			{
				interpreter.pushStr(func._echoOutputs); // so caller can see the 'echo' command outputs
			}
		}
	}
}
