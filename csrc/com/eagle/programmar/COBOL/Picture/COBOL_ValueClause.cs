// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL.Picture
{
	using COBOL_Picture_Value = com.eagle.programmar.COBOL.COBOL_Picture_Value;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ValueClause : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice VALUE = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("VALUE", "VALUES");
		public COBOL_KeywordChoice VALUE = new COBOL_KeywordChoice("VALUE", "VALUES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_KeywordChoice ARE = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ARE", "IS");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Picture_Value> values;
		public TokenList<COBOL_Picture_Value> values;
	}

}
