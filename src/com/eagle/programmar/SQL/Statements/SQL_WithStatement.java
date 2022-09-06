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
	public @S(20) SQL_Identifier_Reference name;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<SQL_Identifier_Reference,PunctuationComma> params;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) SQL_Keyword AS = new SQL_Keyword("AS");
	public @S(70) SQL_Expression expr;
	public @S(80) SQL_SelectStatement selectStmt;
}
