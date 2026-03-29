// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_StatementOrComment = com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
	using SQL_Declare_Definition = com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_ForStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword FOR = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("FOR");
		public SQL_Keyword FOR = new SQL_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition variable;
		public SQL_Declare_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword IN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("IN");
		public SQL_Keyword IN = new SQL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.SQL_Expression start;
		public SQL_Expression start;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.Terminals.SQL_Punctuation dotDot = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation("..");
		public SQL_Punctuation dotDot = new SQL_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.SQL.SQL_Expression finish;
		public SQL_Expression finish;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.SQL.Terminals.SQL_Keyword LOOP1 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LOOP");
		public SQL_Keyword LOOP1 = new SQL_Keyword("LOOP");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment> statements;
		public TokenList<SQL_StatementOrComment> statements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.SQL.Terminals.SQL_Keyword END = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("END");
		public SQL_Keyword END = new SQL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.SQL.Terminals.SQL_Keyword LOOP2 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LOOP");
		public SQL_Keyword LOOP2 = new SQL_Keyword("LOOP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
