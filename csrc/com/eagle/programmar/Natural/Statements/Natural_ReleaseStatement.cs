// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_ReleaseStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/release.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword RELEASE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("RELEASE");
		public @DOC("sm/release.htm") Natural_Keyword RELEASE = new Natural_Keyword("RELEASE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword SET = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SET");
		public Natural_Keyword SET = new Natural_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
		public Natural_Literal literal;
	}

}
