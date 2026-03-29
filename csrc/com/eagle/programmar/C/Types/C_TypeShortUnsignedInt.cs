// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C.Types
{
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_TypeStar = com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class C_TypeShortUnsignedInt : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_KeywordChoice UNSIGNED1 = new com.eagle.programmar.C.Terminals.C_KeywordChoice("signed", "unsigned");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_KeywordChoice SHORT = new com.eagle.programmar.C.Terminals.C_KeywordChoice("long", "short");
		public C_KeywordChoice SHORT = new C_KeywordChoice("long", "short");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Keyword LONG = new com.eagle.programmar.C.Terminals.C_Keyword("long");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_KeywordChoice UNSIGNED2 = new com.eagle.programmar.C.Terminals.C_KeywordChoice("signed", "unsigned");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.Terminals.C_KeywordChoice INT = new com.eagle.programmar.C.Terminals.C_KeywordChoice("int", "double");
		public C_KeywordChoice INT = new C_KeywordChoice("int", "double");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar> stars;
		public  OPT;
	}
}
