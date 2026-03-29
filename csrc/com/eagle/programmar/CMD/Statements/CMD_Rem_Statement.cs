// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_RestOfLine = com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMD_Rem_Statement : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rem.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword REM = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("rem");
		public @DOC("rem.mspx") CMD_Keyword REM = new CMD_Keyword("rem");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_RestOfLine comment;
		public CMD_RestOfLine comment;

		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}
	}

}
