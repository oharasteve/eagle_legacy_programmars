// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_DisplayElement = com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_WriteStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/write.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword WRITE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("WRITE");
		public @DOC("sm/write.htm") Natural_Keyword WRITE = new Natural_Keyword("WRITE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_KeywordChoice TITLE = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("TITLE", "NOTITLE");
		public @OPT Natural_KeywordChoice TITLE = new Natural_KeywordChoice("TITLE", "NOTITLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement> writeWhat;
		public TokenList<Natural_DisplayElement> writeWhat;
	}

}
