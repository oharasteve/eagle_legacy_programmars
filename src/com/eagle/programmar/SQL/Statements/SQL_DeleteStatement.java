// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_DeleteStatement extends TokenSequence
{
	public @S(10) @DOC("sql_delete.asp") SQL_Keyword DELETE = new SQL_Keyword("DELETE");
	public @S(20) SQL_Keyword FROM = new SQL_Keyword("FROM");
	public @S(30) SQL_Identifier_Reference table;
	public @S(40) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
	public @S(50) SQL_Expression condition;
	public @S(60) PunctuationSemicolon semicolon;
}
