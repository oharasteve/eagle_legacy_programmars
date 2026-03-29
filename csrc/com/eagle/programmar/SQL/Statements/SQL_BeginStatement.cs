// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_StatementOrComment = com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_BeginStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword BEGIN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BEGIN");
		public SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) SQL_BeginWhat what;
		public SQL_BeginWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class SQL_BeginWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_BeginEnd extends com.eagle.tokens.TokenSequence
			public class SQL_BeginEnd : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment> statements;
				public TokenList<SQL_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_CommitStatement commit;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword END = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("END");
				public SQL_Keyword END = new SQL_Keyword("END");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class SQL_BeginTransaction extends com.eagle.tokens.TokenSequence
			public class SQL_BeginTransaction : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword TRANSACTION = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TRANSACTION");
				public SQL_Keyword TRANSACTION = new SQL_Keyword("TRANSACTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment> statements;
				public TokenList<SQL_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Terminals.SQL_Keyword COMMIT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("COMMIT");
				public SQL_Keyword COMMIT = new SQL_Keyword("COMMIT");
			}
		}
	}

}
