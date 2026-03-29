// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_SearchStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpssear.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SEARCH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SEARCH");
		public @DOC("rlpssear.htm") COBOL_Keyword SEARCH = new COBOL_Keyword("SEARCH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
		public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference var;
		public COBOL_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_SearchAtEndAction atEnd;
		public @OPT COBOL_SearchAtEndAction atEnd;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<COBOL_SearchWhenClause> whens;
		public TokenList<COBOL_SearchWhenClause> whens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword ENDSEARCH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-SEARCH");
		public @OPT COBOL_Keyword ENDSEARCH = new COBOL_Keyword("END-SEARCH");

		public static class COBOL_SearchAtEndAction extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword AT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("AT");
			public COBOL_Keyword AT = new COBOL_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword END = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END");
			public COBOL_Keyword END = new COBOL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> endAction;
			public TokenList<COBOL_Statement> endAction;
		}

		public static class COBOL_SearchWhenClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WHEN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WHEN");
			public COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression condition;
			public COBOL_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> statements;
			public TokenList<COBOL_Statement> statements;
		}
	}

}
