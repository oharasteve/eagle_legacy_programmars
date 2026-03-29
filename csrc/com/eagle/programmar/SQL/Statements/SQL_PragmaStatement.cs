// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Number = com.eagle.programmar.SQL.Terminals.SQL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_PragmaStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword PRAGMA = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("PRAGMA");
		public SQL_Keyword PRAGMA = new SQL_Keyword("PRAGMA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<SQL_PragmaClause> clauses;
		public TokenList<SQL_PragmaClause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class SQL_PragmaClause : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_Pragma_ForeignKeys extends com.eagle.tokens.TokenSequence
			public class SQL_Pragma_ForeignKeys : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword FOREIGN_KEYS = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("FOREIGN_KEYS");
				public SQL_Keyword FOREIGN_KEYS = new SQL_Keyword("FOREIGN_KEYS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) SQL_Pragma_ForeignKey foreignKey;
				public SQL_Pragma_ForeignKey foreignKey;

				public class SQL_Pragma_ForeignKey : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Number XXnumber;
					public SQL_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Keyword XXOFF = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("OFF");
					public SQL_Keyword XXOFF = new SQL_Keyword("OFF");
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_Pragma_JournalMode extends com.eagle.tokens.TokenSequence
			public class SQL_Pragma_JournalMode : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword JOURNAL_MODE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("JOURNAL_MODE");
				public SQL_Keyword JOURNAL_MODE = new SQL_Keyword("JOURNAL_MODE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword OFF = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("OFF");
				public SQL_Keyword OFF = new SQL_Keyword("OFF");
			}
		}
	}

}
