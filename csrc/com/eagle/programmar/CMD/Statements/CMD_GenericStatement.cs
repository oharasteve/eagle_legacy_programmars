// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Filename = com.eagle.programmar.CMD.Terminals.CMD_Filename;
	using CMD_PunctuationChoice = com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
	using CMD_RawArgument = com.eagle.programmar.CMD.Terminals.CMD_RawArgument;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMD_GenericStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.CMD_Expression programName;
		public CMD_Expression programName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_GenericArgument> args;
		public  OPT;

		public class CMD_GenericArgument : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_PunctuationChoice XXpunct = new com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice(",", "-", "/", "%*");
			public CMD_PunctuationChoice XXpunct = new CMD_PunctuationChoice(",", "-", "/", "%*");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Filename XXfileName;
			public CMD_Filename XXfileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMD_Expression XXexpr;
			public CMD_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMD_RawArgument XXrawArg;
			public CMD_RawArgument XXrawArg;
		}
	}
}
