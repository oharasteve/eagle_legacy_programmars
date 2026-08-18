// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_WithStatement extends TokenSequence
{
	public @S(10) SQL_Keyword WITH = new SQL_Keyword("WITH");
	public @S(20) @OPT SQL_Keyword RECURSIVE = new SQL_Keyword("RECURSIVE");
	public @S(30) SeparatedList<SQL_WithWhat,PunctuationComma> withs;
	public @S(40) @OPT SQL_WithInsert insert;
	public @S(50) SQL_SelectStatement selectStmt;
	
	public static class SQL_WithWhat extends TokenSequence
	{
		public @S(10) SQL_Identifier_Reference name;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<SQL_Identifier_Reference, PunctuationComma> params;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) SQL_Keyword AS = new SQL_Keyword("AS");
		public @S(60) @OPT SQL_Keyword MATERIALIZED = new SQL_Keyword("MATERIALIZED");
		public @S(70) SQL_Expression expr;
	}
	
	public static class SQL_WithInsert extends TokenSequence
	{
		public @S(10) SQL_Keyword INSERT = new SQL_Keyword("INSERT");
		public @S(20) SQL_Keyword INTO = new SQL_Keyword("INTO");
		public @S(30) SQL_Identifier_Reference table;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) SeparatedList<SQL_Identifier_Reference, PunctuationComma> fields;
		public @S(60) PunctuationRightParen rightParen;
	}
}
