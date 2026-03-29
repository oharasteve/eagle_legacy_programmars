// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Role_Definition = com.eagle.programmar.SQL.Symbols.SQL_Role_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_CreateRoleStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CREATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CREATE");
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword ROLE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ROLE");
		public SQL_Keyword ROLE = new SQL_Keyword("ROLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Symbols.SQL_Role_Definition role;
		public SQL_Role_Definition role;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}
}
