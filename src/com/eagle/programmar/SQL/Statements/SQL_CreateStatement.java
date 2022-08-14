// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Statements.SQL_CreateField.SQL_CreateFieldKey;
import com.eagle.programmar.SQL.Statements.SQL_CreateStatement.SQL_CreateTableStatement.SQL_IfNotExists;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Index_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Role_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Synonym_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_View_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_CreateStatement extends TokenChooser
{
	public @CHOICE static class SQL_CreateTableStatement extends TokenSequence
	{
		public @S(10) @DOC("sql_create_table.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(15) @OPT SQL_Keyword VIRTUAL = new SQL_Keyword("VIRTUAL");
		public @S(20) SQL_Keyword TABLE = new SQL_Keyword("TABLE");
		public @S(30) @OPT SQL_IfNotExists ifNotExists;
		public @S(40) SQL_Table_Definition table;
		public @S(50) PunctuationLeftParen leftParen;
		public @S(60) SeparatedList<SQL_CreateField,PunctuationComma> createFields;
		public @S(70) @OPT TokenList<SQL_CreateFieldKey> keys;
		public @S(80) PunctuationRightParen rightParen;
		public @S(90) @OPT TokenList<SQL_CreateOption> options;
		public @S(200) PunctuationSemicolon semicolon;
	
		public static class SQL_IfNotExists extends TokenSequence
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

	public @CHOICE static class SQL_CreateIndexStatement extends TokenSequence
	{
		public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(20) @OPT SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
		public @S(30) SQL_Keyword INDEX = new SQL_Keyword("INDEX");
		public @S(40) @OPT SQL_IfNotExists ifNotExists;
		public @S(50) SQL_Index_Definition index;
		public @S(60) SQL_Keyword ON = new SQL_Keyword("ON");
		public @S(70) SQL_Identifier_Reference table;
		public @S(80) PunctuationLeftParen leftParen;
		public @S(90) SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyFields;
		public @S(100) PunctuationRightParen rightParen;
		public @S(110) @OPT SQL_CreateIndexWhere where;
		public @S(120) PunctuationSemicolon semicolon;
		
		public static class SQL_CreateIndexWhere extends TokenSequence
		{
			public @S(10) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
			public @S(20) SQL_Expression condition;
		}
	}
	
	public @CHOICE static class SQL_CreateViewStatement extends TokenSequence
	{
		public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(20) @OPT SQL_Keyword OR = new SQL_Keyword("OR");
		public @S(30) @OPT SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
		public @S(40) SQL_Keyword VIEW = new SQL_Keyword("VIEW");
		public @S(50) SQL_View_Definition view;
		public @S(60) SQL_Keyword AS = new SQL_Keyword("AS");
		public @S(70) SQL_SelectStatement select;
	}
	
	public @CHOICE static class SQL_CreateRoleStatement extends TokenSequence
	{
		public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(20) SQL_Keyword ROLE = new SQL_Keyword("ROLE");
		public @S(30) SQL_Role_Definition role;
		public @S(40) PunctuationSemicolon semicolon;
	}
	
	public @CHOICE static class SQL_CreateSynonymStatement extends TokenSequence
	{
		public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(20) SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
		public @S(30) SQL_Keyword SYNONYM = new SQL_Keyword("SYNONYM");
		public @S(40) SQL_Synonym_Definition synonym;
		public @S(50) SQL_Keyword FOR = new SQL_Keyword("FOR");
		public @S(60) @OPT SQL_CreateSynonymForWhom whom;
		public @S(70) PunctuationSemicolon semicolon;
		
		public static class SQL_CreateSynonymForWhom extends TokenSequence
		{
			public @S(10) SQL_Punctuation ampersand = new SQL_Punctuation('&');
			public @S(20) SQL_Identifier_Reference user;
			public @S(30) SQL_Punctuation dotDot = new SQL_Punctuation("..");
			public @S(40) SQL_Identifier_Reference group;
		}
	}
}
