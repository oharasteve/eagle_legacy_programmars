// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Literal = com.eagle.programmar.SQL.Terminals.SQL_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class SQL_LoadStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword LOAD = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LOAD");
		public SQL_Keyword LOAD = new SQL_Keyword("LOAD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword DATA = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DATA");
		public SQL_Keyword DATA = new SQL_Keyword("DATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword LOCAL = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LOCAL");
		public SQL_Keyword LOCAL = new SQL_Keyword("LOCAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Terminals.SQL_Keyword INFILE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("INFILE");
		public SQL_Keyword INFILE = new SQL_Keyword("INFILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.Terminals.SQL_Literal inFile;
		public SQL_Literal inFile;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.SQL.Terminals.SQL_Keyword INTO = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("INTO");
		public SQL_Keyword INTO = new SQL_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.SQL.Terminals.SQL_Keyword TABLE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TABLE");
		public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference table;
		public SQL_Identifier_Reference table;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.SQL.Terminals.SQL_Keyword FIELDS = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("FIELDS");
		public SQL_Keyword FIELDS = new SQL_Keyword("FIELDS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.SQL.Terminals.SQL_Keyword TERMINATED1 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TERMINATED");
		public SQL_Keyword TERMINATED1 = new SQL_Keyword("TERMINATED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.SQL.Terminals.SQL_Keyword BY1 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BY");
		public SQL_Keyword BY1 = new SQL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.SQL.Terminals.SQL_Literal fieldTerminator;
		public SQL_Literal fieldTerminator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.programmar.SQL.Terminals.SQL_Keyword ENCLOSED = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ENCLOSED");
		public SQL_Keyword ENCLOSED = new SQL_Keyword("ENCLOSED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.programmar.SQL.Terminals.SQL_Keyword BY2 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BY");
		public SQL_Keyword BY2 = new SQL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) com.eagle.programmar.SQL.Terminals.SQL_Literal enclosure;
		public SQL_Literal enclosure;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) com.eagle.programmar.SQL.Terminals.SQL_Keyword ESCAPED = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ESCAPED");
		public SQL_Keyword ESCAPED = new SQL_Keyword("ESCAPED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) com.eagle.programmar.SQL.Terminals.SQL_Keyword BY3 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BY");
		public SQL_Keyword BY3 = new SQL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) com.eagle.programmar.SQL.Terminals.SQL_Literal escaped;
		public SQL_Literal escaped;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(190) com.eagle.programmar.SQL.Terminals.SQL_Keyword LINES = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LINES");
		public SQL_Keyword LINES = new SQL_Keyword("LINES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(200) com.eagle.programmar.SQL.Terminals.SQL_Keyword TERMINATED2 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TERMINATED");
		public SQL_Keyword TERMINATED2 = new SQL_Keyword("TERMINATED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(210) com.eagle.programmar.SQL.Terminals.SQL_Keyword BY4 = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BY");
		public SQL_Keyword BY4 = new SQL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(220) com.eagle.programmar.SQL.Terminals.SQL_Literal lineTerminator;
		public SQL_Literal lineTerminator;
	}

}
