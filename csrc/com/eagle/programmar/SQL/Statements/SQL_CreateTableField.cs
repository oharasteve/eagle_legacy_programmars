// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.SQL.Statements
{
	using SQL_Constraint = com.eagle.programmar.SQL.SQL_Constraint;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Type = com.eagle.programmar.SQL.SQL_Type;
	using SQL_Field_Definition = com.eagle.programmar.SQL.Symbols.SQL_Field_Definition;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Key_Definition = com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using SQL_Literal = com.eagle.programmar.SQL.Terminals.SQL_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class SQL_CreateTableField : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Symbols.SQL_Field_Definition fieldName;
		public SQL_Field_Definition fieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Type fieldType;
		public SQL_Type fieldType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<SQL_FieldOption> fieldOptions;
		public  OPT;

		public class SQL_FieldOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_KeywordChoice XXoption = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("UNIQUE", "AUTOINCREMENT");
			public SQL_KeywordChoice XXoption = new SQL_KeywordChoice("UNIQUE", "AUTOINCREMENT");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldNotNull extends com.eagle.tokens.TokenSequence
			public class SQL_FieldNotNull : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SQL_KeywordChoice NOT = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("NON", "NOT");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword NULL = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("NULL");
				public SQL_Keyword NULL = new SQL_Keyword("NULL");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldDefault extends com.eagle.tokens.TokenSequence
			public class SQL_FieldDefault : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DEFAULT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DEFAULT");
				public SQL_Keyword DEFAULT = new SQL_Keyword("DEFAULT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Expression value;
				public SQL_Expression value;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldOnAction extends com.eagle.tokens.TokenSequence
			public class SQL_FieldOnAction : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ON = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ON");
				public SQL_Keyword ON = new SQL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice UPDATE = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("UPDATE", "DELETE");
				public SQL_KeywordChoice UPDATE = new SQL_KeywordChoice("UPDATE", "DELETE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.SQL_Expression value;
				public SQL_Expression value;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldComment extends com.eagle.tokens.TokenSequence
			public class SQL_FieldComment : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword COMMENT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("COMMENT");
				public SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Literal comment;
				public SQL_Literal comment;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldKey extends com.eagle.tokens.TokenSequence
			public class SQL_FieldKey : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword PRIMARY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("PRIMARY");
				public SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword KEY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("KEY");
				public SQL_Keyword KEY = new SQL_Keyword("KEY");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldCharSet extends com.eagle.tokens.TokenSequence
			public class SQL_FieldCharSet : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CHARACTER = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CHARACTER");
				public SQL_Keyword CHARACTER = new SQL_Keyword("CHARACTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword SET = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SET");
				public SQL_Keyword SET = new SQL_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice UTF = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("utf8");
				public SQL_KeywordChoice UTF = new SQL_KeywordChoice("utf8");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldCollate extends com.eagle.tokens.TokenSequence
			public class SQL_FieldCollate : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword COLLATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("COLLATE");
				public SQL_Keyword COLLATE = new SQL_Keyword("COLLATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice UTF = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("utf8_unicode_ci");
				public SQL_KeywordChoice UTF = new SQL_KeywordChoice("utf8_unicode_ci");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldOnDelete extends com.eagle.tokens.TokenSequence
			public class SQL_FieldOnDelete : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword REFERENCES = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("REFERENCES");
				public SQL_Keyword REFERENCES = new SQL_Keyword("REFERENCES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Expression value;
				public SQL_Expression value;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FieldDeferrable extends com.eagle.tokens.TokenSequence
			public class SQL_FieldDeferrable : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DEFERRABLE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DEFERRABLE");
				public SQL_Keyword DEFERRABLE = new SQL_Keyword("DEFERRABLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword INITIALLY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("INITIALLY");
				public SQL_Keyword INITIALLY = new SQL_Keyword("INITIALLY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice DEFERRED = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("IMMEDIATE", "DEFERRED");
				public SQL_KeywordChoice DEFERRED = new SQL_KeywordChoice("IMMEDIATE", "DEFERRED");
			}
		}

		public class SQL_CreateFieldKey : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateUsing extends com.eagle.tokens.TokenSequence
			public class SQL_CreateUsing : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword USING = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("USING");
				public SQL_Keyword USING = new SQL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword BTREE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BTREE");
				public SQL_Keyword BTREE = new SQL_Keyword("BTREE");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateFieldPrimaryKey extends com.eagle.tokens.TokenSequence
			public class SQL_CreateFieldPrimaryKey : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword PRIMARY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("PRIMARY");
				public SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword KEY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("KEY");
				public SQL_Keyword KEY = new SQL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> keyFields;
				public SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateFieldPlainKey extends com.eagle.tokens.TokenSequence
			public class SQL_CreateFieldPlainKey : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword KEY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("KEY");
				public SQL_Keyword KEY = new SQL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Symbols.SQL_Key_Definition key;
				public SQL_Key_Definition key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> keyFields;
				public SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateFieldUniqueKey extends com.eagle.tokens.TokenSequence
			public class SQL_CreateFieldUniqueKey : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword UNIQUE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("UNIQUE");
				public SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SQL_CreateFieldUniqueKeyName name;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> keyFields;
				public SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;

				public class SQL_CreateFieldUniqueKeyName : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword KEY = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("KEY");
					public SQL_Keyword KEY = new SQL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Key_Definition key;
					public SQL_Key_Definition key;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_CreateFieldConstraint extends com.eagle.tokens.TokenSequence
			public class SQL_CreateFieldConstraint : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Constraint constraint;
				public SQL_Constraint constraint;
			}
		}
	}
}
