// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2022

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_BasicExpression = com.eagle.programmar.CMD.CMD_BasicExpression;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_KeywordChoice = com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
	using CMD_Literal = com.eagle.programmar.CMD.Terminals.CMD_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_Xcopy_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword XCOPY = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("XCOPY");
		public CMD_Keyword XCOPY = new CMD_Keyword("XCOPY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_XcopyOption> options1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Literal source;
		public CMD_Literal source;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_Literal target;
		public CMD_Literal target;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<CMD_XcopyOption> options2;
		public  OPT;

		public class CMD_XcopyOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_XcopyOptionLetter extends com.eagle.tokens.TokenSequence
			public class Powershell_XcopyOptionLetter : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash XXslash;
				public PunctuationSlash XXslash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice XXopt = new com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice("D", "E", "F", "H", "I", "Q", "R", "S", "V", "Y");
				public CMD_KeywordChoice XXopt = new CMD_KeywordChoice("D", "E", "F", "H", "I", "Q", "R", "S", "V", "Y");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_XcopyOptionExclude extends com.eagle.tokens.TokenSequence
			public class CMD_XcopyOptionExclude : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword EXCLUDE = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("EXCLUDE");
				public CMD_Keyword EXCLUDE = new CMD_Keyword("EXCLUDE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.CMD_BasicExpression filename;
				public CMD_BasicExpression filename;
			}
		}
	}

}
