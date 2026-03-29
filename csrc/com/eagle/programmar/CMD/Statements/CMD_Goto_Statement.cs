// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using CMD_Label = com.eagle.programmar.CMD.CMD_Label;
	using CMD_Program = com.eagle.programmar.CMD.CMD_Program;
	using CMD_CommandOrLabel = com.eagle.programmar.CMD.CMD_Program.CMD_CommandOrLabel;
	using CMD_Identifier_Reference = com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class CMD_Goto_Statement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("goto.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword GOTO = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("goto");
		public @DOC("goto.mspx") CMD_Keyword GOTO = new CMD_Keyword("goto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationColon colon;
		public @OPT PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMD_Goto_What gotoWhat;
		public CMD_Goto_What gotoWhat;

		public static class CMD_Goto_What extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Identifier_Reference XXlabel;
			public CMD_Identifier_Reference XXlabel;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Keyword XXEOF = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("eof");
			public CMD_Keyword XXEOF = new CMD_Keyword("eof");
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (gotoWhat.getWhich() is CMD_Keyword)
			{
				// GOTO :EOF
				CMD_Label func = (CMD_Label) interpreter.getCurrentFunction();
				func._exitStatus = 0; // Meaning Success
				return Eagle_Statement_Result.RETURN;
			}

			CMD_Identifier_Reference label = (CMD_Identifier_Reference) gotoWhat.getWhich();
			// Look it up
			string name = label.getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a label named " + name);
			}
			CMD_Label func = (CMD_Label) fn;

			// Transfer control to the label
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			CMD_Program pgm = (CMD_Program) interpreter._lang;
			bool foundLabel = false;
			foreach (CMD_Program.CMD_CommandOrLabel cmdOr in pgm.commands._elements)
			{
				if (foundLabel)
				{
					// Run the next statement in the program
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
			return result;
		}
	}

}
