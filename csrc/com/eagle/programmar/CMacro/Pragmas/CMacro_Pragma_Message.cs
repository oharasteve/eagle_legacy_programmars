// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.CMacro.Pragmas
{
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using CMacro_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CMacro_Pragma_Message : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword MESSAGE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("message");
		public CMacro_Keyword MESSAGE = new CMacro_Keyword("message");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMacro_Keyword DISABLE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("disable");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<CMacro_PragmaMsg, com.eagle.tokens.punctuation.PunctuationComma> codes;
		public SeparatedList<CMacro_PragmaMsg, PunctuationComma> codes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class CMacro_PragmaMsg : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Literal XXliteral;
			public CMacro_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_KeywordChoice XXUNDERFLOW = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("UNDERFLOW", "FLOATOVERFL", "nosimpint");
			public CMacro_KeywordChoice XXUNDERFLOW = new CMacro_KeywordChoice("UNDERFLOW", "FLOATOVERFL", "nosimpint");
		}
	}

}
