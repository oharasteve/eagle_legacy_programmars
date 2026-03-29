// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

namespace com.eagle.programmar.SQL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using SQL_Table = com.eagle.programmar.SQL.SQL_Table;
	using SQL_Type = com.eagle.programmar.SQL.SQL_Type;
	using SQL_CreateFieldKey = com.eagle.programmar.SQL.Statements.SQL_CreateTableField.SQL_CreateFieldKey;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Table_Definition = com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using SQL_Literal = com.eagle.programmar.SQL.Terminals.SQL_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class SQL_CreateTableStatement : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sql_create_table.asp") com.eagle.programmar.SQL.Terminals.SQL_Keyword CREATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CREATE");
		public @DOC("sql_create_table.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_KeywordChoice VIRTUAL = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("VIRTUAL", "TEMPORARY");
		public @OPT SQL_KeywordChoice VIRTUAL = new SQL_KeywordChoice("VIRTUAL", "TEMPORARY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword TABLE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("TABLE");
		public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SQL_IfTableNotExists ifNotExists;
		public @OPT SQL_IfTableNotExists ifNotExists;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.Symbols.SQL_Table_Definition tableName;
		public SQL_Table_Definition tableName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) SQL_TableType type;
		public SQL_TableType type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public static class SQL_TableType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_TableNormal XXtableNormal;
			public SQL_TableNormal XXtableNormal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_TableUsing XXtableUsing;
			public SQL_TableUsing XXtableUsing;
		}

		public static class SQL_TableNormal extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<SQL_CreateTableField, com.eagle.tokens.punctuation.PunctuationComma> createFields;
			public SeparatedList<SQL_CreateTableField, PunctuationComma> createFields;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.SQL.Statements.SQL_CreateTableField.SQL_CreateFieldKey> keys;
			public @OPT TokenList<SQL_CreateFieldKey> keys;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<SQL_CreateOption> options;
			public @OPT TokenList<SQL_CreateOption> options;
		}

		public static class SQL_TableUsing extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword USING = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("USING");
			public SQL_Keyword USING = new SQL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword SPAN_JOIN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SPAN_JOIN");
			public SQL_Keyword SPAN_JOIN = new SQL_Keyword("SPAN_JOIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> names;
			public SeparatedList<SQL_Identifier_Reference, PunctuationComma> names;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class SQL_IfTableNotExists extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword IF = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("IF");
			public SQL_Keyword IF = new SQL_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword NOT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("NOT");
			public SQL_Keyword NOT = new SQL_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword EXISTS = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("EXISTS");
			public SQL_Keyword EXISTS = new SQL_Keyword("EXISTS");
		}

		public static class SQL_CreateOption extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Keyword XXDEFAULT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DEFAULT");
			public SQL_Keyword XXDEFAULT = new SQL_Keyword("DEFAULT");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateEngine extends com.eagle.tokens.TokenSequence
			public static class SQL_CreateEngine extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ENGINE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ENGINE");
				public SQL_Keyword ENGINE = new SQL_Keyword("ENGINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice MYIASM = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("MyISAM", "InnoDB");
				public SQL_KeywordChoice MYIASM = new SQL_KeywordChoice("MyISAM", "InnoDB");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateCharset extends com.eagle.tokens.TokenSequence
			public static class SQL_CreateCharset extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CHARSET = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CHARSET");
				public SQL_Keyword CHARSET = new SQL_Keyword("CHARSET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice charset = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("latin1", "utf8", "utf8mb4");
				public SQL_KeywordChoice charset = new SQL_KeywordChoice("latin1", "utf8", "utf8mb4");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateCollate extends com.eagle.tokens.TokenSequence
			public static class SQL_CreateCollate extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword COLLATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("COLLATE");
				public SQL_Keyword COLLATE = new SQL_Keyword("COLLATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice collseq = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("utf8_general_ci", "utf8mb4_unicode_ci");
				public SQL_KeywordChoice collseq = new SQL_KeywordChoice("utf8_general_ci", "utf8mb4_unicode_ci");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateComment extends com.eagle.tokens.TokenSequence
			public static class SQL_CreateComment extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword COMMENT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("COMMENT");
				public SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Literal tital;
				public SQL_Literal tital;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateWithoutRowId extends com.eagle.tokens.TokenSequence
			public static class SQL_CreateWithoutRowId extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword WITHOUT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("WITHOUT");
				public SQL_Keyword WITHOUT = new SQL_Keyword("WITHOUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword ROWID = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ROWID");
				public SQL_Keyword ROWID = new SQL_Keyword("ROWID");
			}
		}

		public void interpret(EagleInterpreter interpreter)
		{
			if (!(type.getWhich() is SQL_TableNormal))
			{
				throw new Exception("Can only Create simple Tables");
			}
			SQL_TableNormal normal = (SQL_TableNormal) type.getWhich();

			SQL_Table table = new SQL_Table();

			int numFields = normal.createFields.getPrimaryCount();
			for (int i = 0; i < numFields; i++)
			{
				SQL_CreateTableField fieldDef = normal.createFields.getPrimaryElement(i);
				string colName = fieldDef.fieldName.getValue().ToUpper();
				TypeEnum colType = SQL_Type.findTypeEnum(fieldDef.fieldType);
				table.addColumn(colName, colType);
			}

			interpreter.setSymbol(normal, tableName.getValue().ToUpper(), table);
		}
	}
}
