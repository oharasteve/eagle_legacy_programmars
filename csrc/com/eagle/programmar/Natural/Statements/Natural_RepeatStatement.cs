// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_RepeatStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/repeat.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword REPEAT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("REPEAT");
		public @DOC("sm/repeat.htm") Natural_Keyword REPEAT = new Natural_Keyword("REPEAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDREPEAT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-REPEAT");
		public Natural_Keyword ENDREPEAT = new Natural_Keyword("END-REPEAT");
	}

}
