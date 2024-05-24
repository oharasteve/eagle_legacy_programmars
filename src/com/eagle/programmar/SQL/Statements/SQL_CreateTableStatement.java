// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Statements.SQL_CreateTableField.SQL_CreateFieldKey;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_CreateTableStatement extends TokenSequence
{
	public @S(10) @DOC("sql_create_table.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) @OPT SQL_Keyword VIRTUAL = new SQL_Keyword("VIRTUAL");
	public @S(30) SQL_Keyword TABLE = new SQL_Keyword("TABLE");
	public @S(40) @OPT SQL_IfTableNotExists ifNotExists;
	public @S(50) SQL_Table_Definition table;
	public @S(60) SQL_TableType type;
	public @S(70) PunctuationSemicolon semicolon;

	public static class SQL_TableType extends TokenChooser
	{
		public @CHOICE static class SQL_TableNormal extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) SeparatedList<SQL_CreateTableField, PunctuationComma> createFields;
			public @S(30) @OPT TokenList<SQL_CreateFieldKey> keys;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) @OPT TokenList<SQL_CreateOption> options;
		}

		public @CHOICE static class SQL_TableUsing extends TokenSequence
		{
			public @S(10) SQL_Keyword USING = new SQL_Keyword("USING");
			public @S(20) SQL_Keyword SPAN_JOIN = new SQL_Keyword("SPAN_JOIN");
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) SeparatedList<SQL_Identifier_Reference, PunctuationComma> names;
			public @S(50) PunctuationRightParen rightParen;
		}
	}

	public static class SQL_IfTableNotExists extends TokenSequence
	{
		public @S(10) SQL_Keyword IF = new SQL_Keyword("IF");
		public @S(20) SQL_Keyword NOT = new SQL_Keyword("NOT");
		public @S(30) SQL_Keyword EXISTS = new SQL_Keyword("EXISTS");
	}

	public static class SQL_CreateOption extends TokenChooser
	{
		public @CHOICE SQL_Keyword DEFAULT = new SQL_Keyword("DEFAULT");

		public @CHOICE static class SQL_CreateEngine extends TokenSequence
		{
			public @S(10) SQL_Keyword ENGINE = new SQL_Keyword("ENGINE");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_Keyword MYIASM = new SQL_Keyword("MyISAM");
		}

		public @CHOICE static class SQL_CreateCharset extends TokenSequence
		{
			public @S(10) SQL_Keyword CHARSET = new SQL_Keyword("CHARSET");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_KeywordChoice charset = new SQL_KeywordChoice("latin1", "utf8");
		}

		public @CHOICE static class SQL_CreateCollate extends TokenSequence
		{
			public @S(10) SQL_Keyword COLLATE = new SQL_Keyword("COLLATE");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_KeywordChoice collseq = new SQL_KeywordChoice("utf8_general_ci");
		}

		public @CHOICE static class SQL_CreateComment extends TokenSequence
		{
			public @S(10) SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_Literal tital;
		}

		public @CHOICE static class SQL_CreateWithoutRowId extends TokenSequence
		{
			public @S(10) SQL_Keyword WITHOUT = new SQL_Keyword("WITHOUT");
			public @S(20) SQL_Keyword ROWID = new SQL_Keyword("ROWID");
		}
	}
}