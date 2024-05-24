// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Constraint;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Field_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_CreateTableField extends TokenSequence
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

		public @CHOICE static class SQL_FieldOnAction extends TokenSequence
		{
			public @S(10) SQL_Keyword ON = new SQL_Keyword("ON");
			public @S(20) SQL_KeywordChoice UPDATE = new SQL_KeywordChoice("UPDATE", "DELETE");
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

		public @CHOICE static class SQL_FieldOnDelete extends TokenSequence
		{
			public @S(10) SQL_Keyword REFERENCES = new SQL_Keyword("REFERENCES");
			public @S(20) SQL_Expression value;
		}

		public @CHOICE static class SQL_FieldDeferrable extends TokenSequence
		{
			public @S(10) SQL_Keyword DEFERRABLE = new SQL_Keyword("DEFERRABLE");
			public @S(20) SQL_Keyword INITIALLY = new SQL_Keyword("INITIALLY");
			public @S(30) SQL_KeywordChoice DEFERRED = new SQL_KeywordChoice("IMMEDIATE", "DEFERRED");
		}
	}

	public static class SQL_CreateFieldKey extends TokenChooser
	{
		public @CHOICE static class SQL_CreateUsing extends TokenSequence
		{
			public @S(10) SQL_Keyword USING = new SQL_Keyword("USING");
			public @S(20) SQL_Keyword BTREE = new SQL_Keyword("BTREE");
		}

		public @CHOICE static class SQL_CreateFieldPrimaryKey extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
			public @S(30) SQL_Keyword KEY = new SQL_Keyword("KEY");
			public @S(40) PunctuationLeftParen leftParen;
			public @S(50) SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
			public @S(60) PunctuationRightParen rightParen;
		}

		public @CHOICE static class SQL_CreateFieldPlainKey extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SQL_Keyword KEY = new SQL_Keyword("KEY");
			public @S(30) SQL_Key_Definition key;
			public @S(40) PunctuationLeftParen leftParen;
			public @S(50) SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
			public @S(60) PunctuationRightParen rightParen;
		}

		public @CHOICE static class SQL_CreateFieldUniqueKey extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
			public @S(30) @OPT SQL_CreateFieldUniqueKeyName name;
			public @S(40) PunctuationLeftParen leftParen;
			public @S(50) SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
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
}