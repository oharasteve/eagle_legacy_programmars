// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Constraint = com.eagle.programmar.SQL.SQL_Constraint;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_AlterStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ALTER = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ALTER");
		public SQL_Keyword ALTER = new SQL_Keyword("ALTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) SQL_AlterWhat what;
		public SQL_AlterWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class SQL_AlterWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_AlterTable extends com.eagle.tokens.TokenSequence
			public class SQL_AlterTable : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword TABLE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TABLE");
				public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference table;
				public SQL_Identifier_Reference table;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice ADD = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("ADD");
				public SQL_KeywordChoice ADD = new SQL_KeywordChoice("ADD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.SQL_Constraint constraint;
				public SQL_Constraint constraint;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT SQL_OnDelete onDelete;
				public  OPT;

				public class SQL_OnDelete : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ON = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ON");
					public SQL_Keyword ON = new SQL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword DELETE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DELETE");
					public SQL_Keyword DELETE = new SQL_Keyword("DELETE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword CASCADE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CASCADE");
					public SQL_Keyword CASCADE = new SQL_Keyword("CASCADE");
				}
			}
		}
	}

}
