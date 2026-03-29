// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 24, 2022

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_KeywordChoice = com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
	using CMD_Literal = com.eagle.programmar.CMD.Terminals.CMD_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_FindStr_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword FINDSTR = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("FINDSTR");
		public CMD_Keyword FINDSTR = new CMD_Keyword("FINDSTR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_FindStrOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Literal pattern;
		public CMD_Literal pattern;

		public class CMD_FindStrOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_FindStrOptionLetter extends com.eagle.tokens.TokenSequence
			public class Powershell_FindStrOptionLetter : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice OPT = new com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice("R");
				public CMD_KeywordChoice OPT = new CMD_KeywordChoice("R");
			}
		}
	}

}
