// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C.Types
{
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_TypeStar = com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class C_TypeUserDefined : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword STRUCT = new com.eagle.programmar.C.Terminals.C_Keyword("struct");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Identifier_Reference typeName;
		public C_Identifier_Reference typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar> stars;
		public  OPT;
	}
}
