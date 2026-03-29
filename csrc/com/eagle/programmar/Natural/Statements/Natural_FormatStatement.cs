// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_OptionChoice = com.eagle.programmar.Natural.Natural_Option.Natural_OptionChoice;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_FormatStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/format.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword FORMAT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FORMAT");
		public @DOC("sm/format.htm") Natural_Keyword FORMAT = new Natural_Keyword("FORMAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Option.Natural_OptionChoice> options;
		public TokenList<Natural_OptionChoice> options;
	}

}
