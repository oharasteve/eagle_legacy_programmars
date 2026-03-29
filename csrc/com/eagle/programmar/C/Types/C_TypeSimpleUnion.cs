// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C.Types
{
	using C_Type_Definition = com.eagle.programmar.C.Symbols.C_Type_Definition;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class C_TypeSimpleUnion : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword UNION = new com.eagle.programmar.C.Terminals.C_Keyword("union");
		public C_Keyword UNION = new C_Keyword("union");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Type_Definition def;
		public C_Type_Definition def;
	}
}
