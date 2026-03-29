// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Expression = com.eagle.programmar.Natural.Natural_Expression;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_CompressStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/compress.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword COMPRESS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("COMPRESS");
		public @DOC("sm/compress.htm") Natural_Keyword COMPRESS = new Natural_Keyword("COMPRESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Expression> expr;
		public TokenList<Natural_Expression> expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword INTO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("INTO");
		public Natural_Keyword INTO = new Natural_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Natural_Variable var;
		public Natural_Variable var;
	}

}
