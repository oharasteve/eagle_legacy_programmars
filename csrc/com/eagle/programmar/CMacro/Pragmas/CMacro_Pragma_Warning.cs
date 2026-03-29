// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.CMacro.Pragmas
{
	using CMacro_Identifier = com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using CMacro_Number = com.eagle.programmar.CMacro.Terminals.CMacro_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CMacro_Pragma_Warning : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword WARNING = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("warning");
		public CMacro_Keyword WARNING = new CMacro_Keyword("warning");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationLeftParen leftParen;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice DISABLE = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("disable", "restore", "push", "pop", "default");
		public CMacro_KeywordChoice DISABLE = new CMacro_KeywordChoice("disable", "restore", "push", "pop", "default");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationColon colon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<CMacro_PragmaCode> codes;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PunctuationRightParen rightParen;
		public  OPT;

		public class CMacro_PragmaCode : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationComma XXcomma;
			public PunctuationComma XXcomma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Number XXnumber;
			public CMacro_Number XXnumber; // 1718 1501 0612 3021 4702 etc etc
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Identifier XXcode;
			public CMacro_Identifier XXcode; // CS0618 and CS1718
		}
	}

}
