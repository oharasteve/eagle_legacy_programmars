// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_DoStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/dodoend.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword DO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DO");
		public @DOC("sm/dodoend.htm") Natural_Keyword DO = new Natural_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public @OPT TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword DOEND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DOEND");
		public Natural_Keyword DOEND = new Natural_Keyword("DOEND");
	}

}
