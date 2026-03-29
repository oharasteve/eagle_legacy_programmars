// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_View_Definition = com.eagle.programmar.SQL.Symbols.SQL_View_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class SQL_CreateViewStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CREATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CREATE");
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_Keyword OR = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("OR");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SQL_Keyword REPLACE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("REPLACE");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Terminals.SQL_Keyword VIEW = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("VIEW");
		public SQL_Keyword VIEW = new SQL_Keyword("VIEW");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.Symbols.SQL_View_Definition view;
		public SQL_View_Definition view;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.SQL.Terminals.SQL_Keyword AS = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("AS");
		public SQL_Keyword AS = new SQL_Keyword("AS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) SQL_SelectStatement select;
		public SQL_SelectStatement select;
	}
}
