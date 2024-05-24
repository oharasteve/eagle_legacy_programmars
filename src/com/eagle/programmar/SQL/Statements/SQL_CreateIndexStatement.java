// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Index_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_CreateIndexStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) @OPT SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
	public @S(30) SQL_Keyword INDEX = new SQL_Keyword("INDEX");
	public @S(40) @OPT SQL_IfIndexNotExists ifNotExists;
	public @S(50) SQL_Index_Definition index;
	public @S(60) SQL_Keyword ON = new SQL_Keyword("ON");
	public @S(70) SQL_Identifier_Reference table;
	public @S(80) PunctuationLeftParen leftParen;
	public @S(90) SeparatedList<SQL_Identifier_Reference, PunctuationComma> keyFields;
	public @S(100) PunctuationRightParen rightParen;
	public @S(110) @OPT SQL_CreateIndexWhere where;
	public @S(120) PunctuationSemicolon semicolon;

	public static class SQL_IfIndexNotExists extends TokenSequence
	{
		public @S(10) SQL_Keyword IF = new SQL_Keyword("IF");
		public @S(20) SQL_Keyword NOT = new SQL_Keyword("NOT");
		public @S(30) SQL_Keyword EXISTS = new SQL_Keyword("EXISTS");
	}

	public static class SQL_CreateIndexWhere extends TokenSequence
	{
		public @S(10) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
		public @S(20) SQL_Expression condition;
	}
}