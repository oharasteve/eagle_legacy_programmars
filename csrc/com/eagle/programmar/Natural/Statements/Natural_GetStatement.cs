// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Expression = com.eagle.programmar.Natural.Natural_Expression;
	using Natural_Label = com.eagle.programmar.Natural.Natural_Label;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_GetStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("sm/get.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword GET = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("GET");
		public @DOC("sm/get.htm") Natural_Keyword GET = new Natural_Keyword("GET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword TRANSACTION = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("TRANSACTION");
		public @OPT Natural_Keyword TRANSACTION = new Natural_Keyword("TRANSACTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Keyword DATA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DATA");
		public @OPT Natural_Keyword DATA = new Natural_Keyword("DATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Expression> exprs;
		public TokenList<Natural_Expression> exprs;
	}

}
