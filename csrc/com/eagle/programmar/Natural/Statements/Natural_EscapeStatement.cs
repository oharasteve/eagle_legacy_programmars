// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_EscapeStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/escape.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword ESCAPE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ESCAPE");
		public @DOC("sm/escape.htm") Natural_Keyword ESCAPE = new Natural_Keyword("ESCAPE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice what = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("BOTTOM", "TOP");
		public Natural_KeywordChoice what = new Natural_KeywordChoice("BOTTOM", "TOP");
	}

}
