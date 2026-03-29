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
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CMacro_Pragma_CLang : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice CLANG = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("clang", "GCC");
		public CMacro_KeywordChoice CLANG = new CMacro_KeywordChoice("clang", "GCC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice DIAGNOSTIC = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("diagnostic", "optimize");
		public CMacro_KeywordChoice DIAGNOSTIC = new CMacro_KeywordChoice("diagnostic", "optimize");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMacro_Pragma_CLang_What what;
		public CMacro_Pragma_CLang_What what;

		public class CMacro_Pragma_CLang_What : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Literal XXliteral;
			public CMacro_Literal XXliteral;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_KeywordChoice XXPUSH = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("push", "pop");
			public CMacro_KeywordChoice XXPUSH = new CMacro_KeywordChoice("push", "pop");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMacro_Pragma_CLangOptimize extends com.eagle.tokens.TokenSequence
			public class CMacro_Pragma_CLangOptimize : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Literal literal;
				public CMacro_Literal literal; // Such as "fp-contract=off"
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMacro_Pragma_CLangIgnored extends com.eagle.tokens.TokenSequence
			public class CMacro_Pragma_CLangIgnored : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword IGNORED = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("ignored");
				public CMacro_Keyword IGNORED = new CMacro_Keyword("ignored");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Literal warning;
				public CMacro_Literal warning; // e.g., "-Wunguarded-availability"
			}
		}
	}
}
