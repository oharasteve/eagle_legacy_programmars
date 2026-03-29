// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

namespace com.eagle.programmar.Gupta.Statements
{
	using Gupta_Condition = com.eagle.programmar.Gupta.Gupta_Condition;
	using Gupta_Statement = com.eagle.programmar.Gupta.Gupta_Statement;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Gupta_If_Statement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword If = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("If");
		public Gupta_Keyword If = new Gupta_Keyword("If");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Gupta_Condition condition;
		public Gupta_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Statement> thenStatements;
		public TokenList<Gupta_Statement> thenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Gupta_Else_Statement elseClause;
		public  OPT;

		public class Gupta_Else_Statement : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Else = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Else");
			public Gupta_Keyword Else = new Gupta_Keyword("Else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Statement> elseStatements;
			public TokenList<Gupta_Statement> elseStatements;
		}
	}

}
