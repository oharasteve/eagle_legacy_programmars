// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.JavaP.Parameters
{
	using JavaP_Syntax = com.eagle.programmar.JavaP.JavaP_Syntax;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_HexNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class JavaP_OneClassFlags : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword FLAGS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("flags");
		public JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT JavaP_OneFlagCode flagCode;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<JavaP_OneClassFlag, com.eagle.tokens.punctuation.PunctuationComma> flags;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;

		public class JavaP_OneFlagCode : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber hex;
			public JavaP_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class JavaP_OneClassFlag : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_KeywordChoice XXACC = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice(com.eagle.programmar.JavaP.JavaP_Syntax.ACC_CODES);
			public JavaP_KeywordChoice XXACC = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
		}
	}
}
