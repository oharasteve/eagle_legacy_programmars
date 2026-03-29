// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

namespace com.eagle.programmar.CMD.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using CMD_BasicExpression = com.eagle.programmar.CMD.CMD_BasicExpression;
	using CMD_Label = com.eagle.programmar.CMD.CMD_Label;
	using CMD_Program = com.eagle.programmar.CMD.CMD_Program;
	using CMD_CommandOrLabel = com.eagle.programmar.CMD.CMD_Program.CMD_CommandOrLabel;
	using CMD_Label_Reference = com.eagle.programmar.CMD.Symbols.CMD_Label_Reference;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_PunctuationChoice = com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class CMD_Call_Statement : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("call.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword CALL = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("call");
		public @DOC("call.mspx") CMD_Keyword CALL = new CMD_Keyword("call");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationColon colon;
		public @OPT PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Symbols.CMD_Label_Reference label;
		public CMD_Label_Reference label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CMD_Call_Argument> args;
		public @OPT TokenList<CMD_Call_Argument> args;

		public static class CMD_Call_Argument extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_ExpressionComma XXexpressionComma;
			public CMD_ExpressionComma XXexpressionComma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Call_Option XXcallOption;
			public CMD_Call_Option XXcallOption;
		}

		public static class CMD_ExpressionComma extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.CMD_BasicExpression arg;
			public CMD_BasicExpression arg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
		}

		public static class CMD_Call_Option extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice minus = new com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice("-", "/");
			public CMD_PunctuationChoice minus = new CMD_PunctuationChoice("-", "/");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.CMD_BasicExpression option;
			public CMD_BasicExpression option;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// Look it up
			string name = label.getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a label named " + name);
			}
			CMD_Label func = (CMD_Label) fn;
			// AbstractFunction saveFunc = interpreter._currentFunction; // Often null
			interpreter.setCurrentFunction(func);

			// Now assign all the parameters (%1 %2 etc)
			int argCount = 0;
			List<TypeEnum> argTypes = new List<TypeEnum>();
			if (args != null && args.isPresent())
			{
				foreach (CMD_Call_Argument arg in args._elements)
				{
					if (arg.getWhich() is CMD_ExpressionComma)
					{
						CMD_ExpressionComma argComma = (CMD_ExpressionComma) arg.getWhich();
						argCount++;
						EagleValue val = interpreter.getEagleValue(argComma.arg);
						interpreter.setSymbol(arg, "%~" + argCount, val);
						argTypes.Add(val.getType());
					}
				}
			}

			// Prepare to evaluate the label
			long startTime = System.nanoTime();

			// And transfer control to the label
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			CMD_Program pgm = (CMD_Program) interpreter._lang;
			bool foundLabel = false;
			foreach (CMD_Program.CMD_CommandOrLabel cmdOr in pgm.commands._elements)
			{
				if (foundLabel)
				{
					result = interpreter.tryToInterpret(cmdOr);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				else // Have to search for our label (aka function)
				{
					if (cmdOr.getWhich() is CMD_Label)
					{
						CMD_Label lbl = (CMD_Label) cmdOr.getWhich();
						if (lbl == func) // Careful, comparing Objects here
						{
							foundLabel = true;
						}
					}
				}
			}
			if (!foundLabel)
			{
				throw new Exception("Unable to re-find label " + name);
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Remove all the parameter values
		}
	}

}
