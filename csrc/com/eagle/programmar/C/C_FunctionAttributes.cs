// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C
{
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Number = com.eagle.programmar.C.Terminals.C_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class C_FunctionAttributes : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword ATTRIBUTE = new com.eagle.programmar.C.Terminals.C_Keyword("__attribute__");
		public C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
		public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
		public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) C_FunctionAttribute attrib;
		public C_FunctionAttribute attrib;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<C_FunctionMoreAttributes> more;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
		public PunctuationRightParen rightParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
		public PunctuationRightParen rightParen2;

		public class C_FunctionAttribute : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_KeywordChoice XXATTR = new com.eagle.programmar.C.Terminals.C_KeywordChoice("deprecated", "__const__", "__deprecated__", "__leaf__", "__malloc__", "__noreturn__", "__nothrow__", "__pure__", "__warn_unused_result__");
			public C_KeywordChoice XXATTR = new C_KeywordChoice("deprecated", "__const__", "__deprecated__", "__leaf__", "__malloc__", "__noreturn__", "__nothrow__", "__pure__", "__warn_unused_result__");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionAttributeFormat extends com.eagle.tokens.TokenSequence
			public class C_FunctionAttributeFormat : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword FORMAT = new com.eagle.programmar.C.Terminals.C_Keyword("__format__");
				public C_Keyword FORMAT = new C_Keyword("__format__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_KeywordChoice FMTS = new com.eagle.programmar.C.Terminals.C_KeywordChoice("__printf__", "__scanf__", "__strfmon__");
				public C_KeywordChoice FMTS = new C_KeywordChoice("__printf__", "__scanf__", "__strfmon__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma1;
				public PunctuationComma comma1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.Terminals.C_Number number1;
				public C_Number number1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationComma comma2;
				public PunctuationComma comma2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.C.Terminals.C_Number number2;
				public C_Number number2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionAttributeNonNull extends com.eagle.tokens.TokenSequence
			public class C_FunctionAttributeNonNull : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword NONNULL = new com.eagle.programmar.C.Terminals.C_Keyword("__nonnull__");
				public C_Keyword NONNULL = new C_Keyword("__nonnull__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.C.Terminals.C_Number, com.eagle.tokens.punctuation.PunctuationComma> numbers;
				public SeparatedList<C_Number, PunctuationComma> numbers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionAttributeAllocSize extends com.eagle.tokens.TokenSequence
			public class C_FunctionAttributeAllocSize : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword ALLOCSIZE = new com.eagle.programmar.C.Terminals.C_Keyword("__alloc_size__");
				public C_Keyword ALLOCSIZE = new C_Keyword("__alloc_size__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.C.Terminals.C_Number, com.eagle.tokens.punctuation.PunctuationComma> numbers;
				public SeparatedList<C_Number, PunctuationComma> numbers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionAttributeSentinel extends com.eagle.tokens.TokenSequence
			public class C_FunctionAttributeSentinel : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword sentinel = new com.eagle.programmar.C.Terminals.C_Keyword("__sentinel__");
				public C_Keyword sentinel = new C_Keyword("__sentinel__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Number number;
				public C_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}

		public class C_FunctionMoreAttributes : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_FunctionAttribute attrib;
			public C_FunctionAttribute attrib;
		}
	}
}
