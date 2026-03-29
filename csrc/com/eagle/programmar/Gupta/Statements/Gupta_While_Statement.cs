// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2011

namespace com.eagle.programmar.Gupta.Statements
{
	using Gupta_Condition = com.eagle.programmar.Gupta.Gupta_Condition;
	using Gupta_Statement = com.eagle.programmar.Gupta.Gupta_Statement;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Gupta_While_Statement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword While = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("While");
		public Gupta_Keyword While = new Gupta_Keyword("While");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Gupta_Condition condition;
		public Gupta_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Statement> statements;
		public TokenList<Gupta_Statement> statements;
	}

}
