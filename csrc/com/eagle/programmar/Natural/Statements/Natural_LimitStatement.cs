// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 7, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_LimitStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/limit.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword LIMIT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("LIMIT");
		public @DOC("sm/limit.htm") Natural_Keyword LIMIT = new Natural_Keyword("LIMIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Number count;
		public Natural_Number count;
	}

}
