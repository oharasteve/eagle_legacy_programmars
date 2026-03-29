// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2022

namespace com.eagle.programmar.C
{
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Literal = com.eagle.programmar.C.Terminals.C_Literal;
	using C_Number = com.eagle.programmar.C.Terminals.C_Number;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class C_Generic : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation lessThan = new com.eagle.programmar.C.Terminals.C_Punctuation('<');
		public C_Punctuation lessThan = new C_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<C_GenericType, com.eagle.tokens.punctuation.PunctuationComma> types;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Punctuation greaterThan = new com.eagle.programmar.C.Terminals.C_Punctuation('>');
		public C_Punctuation greaterThan = new C_Punctuation('>');

		public class C_GenericType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Number XXnumber;
			public C_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Literal XXliteral;
			public C_Literal XXliteral;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_Plus_GenericType extends com.eagle.tokens.TokenSequence
			public class C_Plus_GenericType : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type type;
				public C_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Punctuation ellipsis = new com.eagle.programmar.C.Terminals.C_Punctuation("...");
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class C_Plus_GenericVoid extends com.eagle.tokens.TokenSequence
			public class C_Plus_GenericVoid : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword VOID = new com.eagle.programmar.C.Terminals.C_Keyword("void");
				public C_Keyword VOID = new C_Keyword("void");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<C_Type, com.eagle.tokens.punctuation.PunctuationComma> types;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}
	}

}
