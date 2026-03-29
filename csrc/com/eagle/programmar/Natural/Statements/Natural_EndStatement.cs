// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_EndStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/endtrans.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword END = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END");
		public @DOC("sm/endtrans.htm") Natural_Keyword END = new Natural_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
		public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword TRANSACTION = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("TRANSACTION");
		public Natural_Keyword TRANSACTION = new Natural_Keyword("TRANSACTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Natural.Natural_Variable> vars;
		public @OPT TokenList<Natural_Variable> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_Literal literal;
		public @OPT Natural_Literal literal;
	}

}
