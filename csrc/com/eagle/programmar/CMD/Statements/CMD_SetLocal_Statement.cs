// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_KeywordChoice = com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class CMD_SetLocal_Statement : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("setlocal.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword SETLOCAL = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("setlocal");
		public @DOC("setlocal.mspx") CMD_Keyword SETLOCAL = new CMD_Keyword("setlocal");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMD_KeywordChoice setWhat = new com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice("ENABLEEXTENSIONS", "ENABLEDELAYEDEXPANSION");
		public @OPT CMD_KeywordChoice setWhat = new CMD_KeywordChoice("ENABLEEXTENSIONS", "ENABLEDELAYEDEXPANSION");

		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}
	}

}
