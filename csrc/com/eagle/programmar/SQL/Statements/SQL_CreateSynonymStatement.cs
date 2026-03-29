// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Synonym_Definition = com.eagle.programmar.SQL.Symbols.SQL_Synonym_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_CreateSynonymStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CREATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CREATE");
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword PUBLIC = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("PUBLIC");
		public SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword SYNONYM = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SYNONYM");
		public SQL_Keyword SYNONYM = new SQL_Keyword("SYNONYM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Symbols.SQL_Synonym_Definition synonym;
		public SQL_Synonym_Definition synonym;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.Terminals.SQL_Keyword FOR = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("FOR");
		public SQL_Keyword FOR = new SQL_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT SQL_CreateSynonymForWhom whom;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class SQL_CreateSynonymForWhom : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Punctuation ampersand = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation('&');
			public SQL_Punctuation ampersand = new SQL_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference user;
			public SQL_Identifier_Reference user;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Punctuation dotDot = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation("..");
			public SQL_Punctuation dotDot = new SQL_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference group;
			public SQL_Identifier_Reference group;
		}
	}
}
