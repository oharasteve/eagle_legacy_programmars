// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Bash_Statement;
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
			if (interpreter._TRACE) System.err.println("*** Calling " + name + "()");
	
			// Have to search for the FUNCTION definition
			Bash_Function_Explicit func = null;
			for (AbstractFunction absFn : interpreter._functionList)
			{
				Bash_Function_Explicit fn = (Bash_Function_Explicit) absFn;
				if (fn.fnName.getValue().equalsIgnoreCase(name))
				{
					// Found it!
					func = fn;
					break;
				}
			}
	
			if (func == null)
			{
				throw new RuntimeException("Unable to find a Function named " + name);
			}
			AbstractFunction saveFunc = interpreter._currentFunction;	// Often null
			interpreter._currentFunction = func;	// Place to save exist code and string outputs
			func._exitStatus = 0;
			func._echoOutputs = null;
	
			int argCount = args.size();
	
			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Bash_FunctionArg arg = args._elements.get(i);
				String paramName = "$" + (i+1);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter._symbolTable.setSymbol(getFileName(), getStartLine(), getStartChar(),
						paramName, val);
			}
	
			// Prepare to evaluate the function
			long startTime = System.nanoTime();
	
			// And transfer control to the function
			for (Bash_Statement stmt : func.statements._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
	
			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);
	
			// Now remove all those parameters
			for (int i = 0; i < argCount; i++)
			{
				String paramName = "$" + (i+1);
				interpreter._symbolTable.removeSymbols(paramName);
			}

			EagleInteger code = new EagleInteger(func._exitStatus);
			interpreter._symbolTable.setSymbol(getFileName(), getStartLine(), getStartChar(), "$?", code);
			
			interpreter._currentFunction = saveFunc;	// Restore previous value
			if (func._echoOutputs != null)
			{
				interpreter.pushStr(func._echoOutputs);  // so caller can see the 'echo' command outputs
			}
		}
	}
}
