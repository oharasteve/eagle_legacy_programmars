// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_InsertStatement extends TokenSequence
{
	public @S(10) @DOC("sql_insert.asp") SQL_Keyword INSERT = new SQL_Keyword("INSERT");
	public @S(20) SQL_Keyword INTO = new SQL_Keyword("INTO");
	public @S(30) SQL_Identifier_Reference table;
	public @S(40) SQL_InsertClause clause;
	public @S(50) PunctuationSemicolon semicolon;
	
	public static class SQL_InsertClause extends TokenChooser
	{
		public @CHOICE static class SQL_InsertSet extends TokenSequence
		{
			public @S(10) SQL_Keyword SET = new SQL_Keyword("SET");
			public @S(20) SeparatedList<SQL_InsertAssignment,PunctuationComma> assignments;

			public static class SQL_InsertAssignment extends TokenSequence
			{
				public @S(10) SQL_Identifier_Reference var;
				public @S(20) PunctuationEquals equals;
				public @S(30) SQL_Expression value;
			}
		}
		
		public @CHOICE static class SQL_InsertValues extends TokenSequence
		{
			public @S(10) @OPT SQL_InsertNames insertNames;
			public @S(20) SQL_Keyword VALUES = new SQL_Keyword("VALUES");
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) SeparatedList<SQL_Expression,PunctuationComma> values;
			public @S(50) PunctuationRightParen rightParen;
			
			public static class SQL_InsertNames extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) SeparatedList<SQL_Identifier_Reference,PunctuationComma> vars;
				public @S(30) PunctuationRightParen rightParen;
			}
		}
	}
}
