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
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_ColumnStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice COLUMN = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("COLUMN", "COL");
		public SQL_KeywordChoice COLUMN = new SQL_KeywordChoice("COLUMN", "COL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference column;
		public SQL_Identifier_Reference column;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword NEWVALUE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("NEW_VALUE");
		public SQL_Keyword NEWVALUE = new SQL_Keyword("NEW_VALUE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.SQL_Expression value;
		public SQL_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
