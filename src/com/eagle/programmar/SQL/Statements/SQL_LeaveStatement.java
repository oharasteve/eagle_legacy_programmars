// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_LeaveStatement extends TokenSequence
{
	public @S(10) SQL_Keyword LEAVE = new SQL_Keyword("LEAVE");
	public @S(20) SQL_Identifier_Reference label;
	public @S(30) PunctuationSemicolon semicolon;
}
