// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

namespace com.eagle.programmar.Java
{
	using Java_Variable_Definition = com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using Java_Punctuation = com.eagle.programmar.Java.Terminals.Java_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_ParameterList : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE SeparatedList<Java_MethodParameter, com.eagle.tokens.punctuation.PunctuationComma> params;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class Java_MethodParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Java_MethodParameterPrefix> prefixes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_Type jtype;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_Punctuation elipsis = new com.eagle.programmar.Java.Terminals.Java_Punctuation("...");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Java.Symbols.Java_Variable_Definition id;
			public Java_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Java_EmptySubscript> emptySubscripts;
			public  OPT;

			public class Java_EmptySubscript : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation emptySubscript = new com.eagle.programmar.Java.Terminals.Java_Punctuation("[]");
				public Java_Punctuation emptySubscript = new Java_Punctuation("[]");
			}

			public class Java_MethodParameterPrefix : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_KeywordChoice XXFINAL = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("final", "static");
				public Java_KeywordChoice XXFINAL = new Java_KeywordChoice("final", "static");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Annotation XXannotation;
				public Java_Annotation XXannotation;
			}
		}
	}

}
