// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_ValuesStatement extends TokenSequence
{
	public @S(10) SQL_Keyword VALUES = new SQL_Keyword("VALUES");
	public @S(20) SeparatedList<SQL_Expression, PunctuationComma> values;
	public @S(30) @OPT SQL_Values_Union union;
	public @S(40) @OPT PunctuationSemicolon semicolon;
	
	public static class SQL_Values_Union extends TokenSequence
	{
		public @S(10) SQL_Keyword UNION = new SQL_Keyword("UNION");
		public @S(20) SQL_Keyword ALL = new SQL_Keyword("ALL");
		public @S(30) SQL_SelectStatement select;
	}
}
