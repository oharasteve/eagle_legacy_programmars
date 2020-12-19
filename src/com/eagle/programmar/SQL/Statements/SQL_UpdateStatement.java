// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_UpdateStatement extends TokenSequence
{
	public @S(10) @DOC("sql_update.asp") SQL_Keyword UPDATE = new SQL_Keyword("UPDATE");
	public @S(20) SQL_Identifier_Reference table;
	public @S(30) SQL_Keyword SET = new SQL_Keyword("SET");
	public @S(40) SeparatedList<SQL_UpdateAssignment,PunctuationComma> assignments;
	public @S(50) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
	public @S(60) SQL_Expression condition;
	public @S(70) PunctuationSemicolon semicolon;

	public static class SQL_UpdateAssignment extends TokenSequence
	{
		public @S(10) SQL_Identifier_Reference var;
		public @S(20) PunctuationEquals equals;
		public @S(30) SQL_Expression value;
	}
}
