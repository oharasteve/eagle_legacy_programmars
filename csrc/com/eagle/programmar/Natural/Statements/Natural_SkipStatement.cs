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
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_SkipStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/skip.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword SKIP = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SKIP");
		public @DOC("sm/skip.htm") Natural_Keyword SKIP = new Natural_Keyword("SKIP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Expression amount;
		public Natural_Expression amount;
	}

}
