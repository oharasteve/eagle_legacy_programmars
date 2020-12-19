// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_ColumnStatement extends TokenSequence
{
	public @S(10) SQL_KeywordChoice COLUMN = new SQL_KeywordChoice("COLUMN", "COL");
	public @S(20) SQL_Identifier_Reference column;
	public @S(30) SQL_Keyword NEWVALUE = new SQL_Keyword("NEW_VALUE");
	public @S(40) SQL_Expression value;
	public @S(50) PunctuationSemicolon semicolon;
}
