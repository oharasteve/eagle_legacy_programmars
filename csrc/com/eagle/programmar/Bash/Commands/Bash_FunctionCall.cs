// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Bash.Commands
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Element = com.eagle.programmar.Bash.Bash_Element;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Function_Explicit = com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
	using Bash_Filename = com.eagle.programmar.Bash.Terminals.Bash_Filename;
	using Bash_Literal = com.eagle.programmar.Bash.Terminals.Bash_Literal;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Bash_FunctionCall : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Bash_WhatFunction what;
		public Bash_WhatFunction what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_FunctionArg> args;
		public  OPT;

		public class Bash_WhatFunction : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Filename XXfilName;
			public Bash_Filename XXfilName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Variable XXvariable;
			public Bash_Variable XXvariable;
		}

		public class Bash_FunctionArg : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Literal XXliteral;
			public Bash_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Number XXnumber;
			public Bash_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Filename XXfileName;
			public Bash_Filename XXfileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Variable XXvariable;
			public Bash_Variable XXvariable;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (what.getWhich() is Bash_Variable)
			{
				Bash_Variable var = (Bash_Variable) what.getWhich();
				string name = var.id.getValue();

				// Have to search for the FUNCTION definition
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn == null)
				{
					throw new Exception("Unable to find a function named " + name);
				}
				Bash_Function_Explicit func = (Bash_Function_Explicit) fn;
				AbstractFunction saveFunc = interpreter.getCurrentFunction(); // Often null
				interpreter.setCurrentFunction(func); // Place to save exist code and string outputs
				func._exitStatus = 0;
				func._echoOutputs = null;

				int argCount = args.size();

				List<TypeEnum> argTypes = new List<TypeEnum>();

				// Now assign all the parameters
				for (int i = 0; i < argCount; i++)
				{
					Bash_FunctionArg arg = args._elements.get(i);
					string paramName = "$" + (i + 1);
					EagleValue val = interpreter.getEagleValue(arg);
					interpreter.setSymbol(this, paramName, val);
					argTypes.Add(val.getType());
				}

				// Prepare to evaluate the function
				long startTime = System.nanoTime();

				// And transfer control to the function
				interpreter.callingFunction(name, func);
				foreach (Bash_Element stmt in func.statements._elements)
				{
					Eagle_Statement_Result result = interpreter.tryToInterpret(stmt.element);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
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

}
