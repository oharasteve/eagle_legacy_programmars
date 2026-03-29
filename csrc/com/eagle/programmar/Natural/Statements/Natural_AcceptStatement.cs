// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Condition = com.eagle.programmar.Natural.Natural_Condition;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_AcceptStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/accept.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword ACCEPT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ACCEPT");
		public @DOC("sm/accept.htm") Natural_Keyword ACCEPT = new Natural_Keyword("ACCEPT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword IF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("IF");
		public Natural_Keyword IF = new Natural_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Condition cond;
		public Natural_Condition cond;
	}

}
