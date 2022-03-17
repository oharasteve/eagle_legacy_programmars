// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Constraint;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Field_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Index_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
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
		
		public static class SQL_CreateField extends TokenSequence
		{
			public @S(10) SQL_Field_Definition fieldName;
			public @S(20) SQL_Type fieldType;
			public @S(30) @OPT TokenList<SQL_FieldOption> fieldOptions;
			
			public static class SQL_FieldOption extends TokenChooser
			{
				public @CHOICE SQL_KeywordChoice option = new SQL_KeywordChoice("UNIQUE", "AUTOINCREMENT");
				
				public @CHOICE static class SQL_FieldNotNull extends TokenSequence
				{
					public @S(10) @OPT SQL_KeywordChoice NOT = new SQL_KeywordChoice("NON", "NOT");
					public @S(20) SQL_Keyword NULL = new SQL_Keyword("NULL");
				}
				
				public @CHOICE static class SQL_FieldDefault extends TokenSequence
				{
					public @S(10) SQL_Keyword DEFAULT = new SQL_Keyword("DEFAULT");
					public @S(20) SQL_Expression value;
				}
				
				public @CHOICE static class SQL_FieldOnUpdate extends TokenSequence
				{
					public @S(10) SQL_Keyword ON = new SQL_Keyword("ON");
					public @S(20) SQL_Keyword UPDATE = new SQL_Keyword("UPDATE");
					public @S(30) SQL_Expression value;
				}
				
				public @CHOICE static class SQL_FieldComment extends TokenSequence
				{
					public @S(10) SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
					public @S(20) SQL_Literal comment;
				}
				
				public @CHOICE static class SQL_FieldKey extends TokenSequence
				{
					public @S(10) SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
					public @S(20) SQL_Keyword KEY = new SQL_Keyword("KEY");
				}
				
				public @CHOICE static class SQL_FieldCharSet extends TokenSequence
				{
					public @S(10) SQL_Keyword CHARACTER = new SQL_Keyword("CHARACTER");
					public @S(20) SQL_Keyword SET = new SQL_Keyword("SET");
					public @S(30) SQL_KeywordChoice UTF = new SQL_KeywordChoice("utf8");
				}
				
				public @CHOICE static class SQL_FieldCollate extends TokenSequence
				{
					public @S(10) SQL_Keyword COLLATE = new SQL_Keyword("COLLATE");
					public @S(20) SQL_KeywordChoice UTF = new SQL_KeywordChoice("utf8_unicode_ci");
				}
			}
		}
		
		public static class SQL_CreateFieldKey extends TokenChooser
		{
			public @CHOICE static class SQL_CreateFieldPrimaryKey extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
				public @S(30) SQL_Keyword KEY = new SQL_Keyword("KEY");
				public @S(40) PunctuationLeftParen leftParen;
				public @S(50) SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyFields;
				public @S(60) PunctuationRightParen rightParen;
			}
	
			public @CHOICE static class SQL_CreateFieldPlainKey extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) SQL_Keyword KEY = new SQL_Keyword("KEY");
				public @S(30) SQL_Key_Definition key;
				public @S(40) PunctuationLeftParen leftParen;
				public @S(50) SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyFields;
				public @S(60) PunctuationRightParen rightParen;
			}
	
			public @CHOICE static class SQL_CreateFieldUniqueKey extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
				public @S(30) @OPT SQL_CreateFieldUniqueKeyName name;
				public @S(40) PunctuationLeftParen leftParen;
				public @S(50) SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyFields;
				public @S(60) PunctuationRightParen rightParen;

				public static class SQL_CreateFieldUniqueKeyName extends TokenSequence
				{
					public @S(10) SQL_Keyword KEY = new SQL_Keyword("KEY");
					public @S(20) SQL_Key_Definition key;
				}
			}
			
			public @CHOICE static class SQL_CreateFieldConstraint extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) SQL_Constraint constraint;
			}
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
		}
	}
	
	public @CHOICE static class SQL_CreateIndexStatement extends TokenSequence
	{
		public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @S(20) @OPT SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
		public @S(30) SQL_Keyword INDEX = new SQL_Keyword("INDEX");
		public @S(40) SQL_Index_Definition index;
		public @S(50) SQL_Keyword ON = new SQL_Keyword("ON");
		public @S(60) SQL_Identifier_Reference table;
		public @S(70) PunctuationLeftParen leftParen;
		public @S(80) SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyFields;
		public @S(90) PunctuationRightParen rightParen;
		public @S(100) PunctuationSemicolon semicolon;
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
