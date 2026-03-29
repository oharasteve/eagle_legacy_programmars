// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using MSSolution_EndOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
	using MSSolution_Keyword = com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
	using MSSolution_Literal = com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class MSSolution_Project : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword PROJECT = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Project");
		public MSSolution_Keyword PROJECT = new MSSolution_Keyword("Project");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal guid1;
		public MSSolution_Literal guid1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal name;
		public MSSolution_Literal name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationComma comma1;
		public PunctuationComma comma1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal projectLocation;
		public MSSolution_Literal projectLocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationComma comma2;
		public PunctuationComma comma2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal guid2;
		public MSSolution_Literal guid2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln1;
		public MSSolution_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT TokenList<MSSolution_ProjectSection> projectSection;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ENDPROJECT = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("EndProject");
		public MSSolution_Keyword ENDPROJECT = new MSSolution_Keyword("EndProject");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln2;
		public MSSolution_EndOfLine eoln2;
	}

}
