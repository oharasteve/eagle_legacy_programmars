// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Option = com.eagle.programmar.Natural.Natural_Option;
	using Natural_DisplayElement = com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_InputStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/input.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword INPUT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("INPUT");
		public @DOC("sm/input.htm") Natural_Keyword INPUT = new Natural_Keyword("INPUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword NO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NO");
		public @OPT Natural_Keyword NO = new Natural_Keyword("NO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword ERASE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ERASE");
		public @OPT Natural_Keyword ERASE = new Natural_Keyword("ERASE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Option option;
		public @OPT Natural_Option option;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement> items;
		public TokenList<Natural_DisplayElement> items;
	}

}
