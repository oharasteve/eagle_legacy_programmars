// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 18, 2026

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_AnalyzeStatement extends TokenSequence
{
	public @S(10) SQL_Keyword ANALYZE = new SQL_Keyword("ANALYZE");
	public @S(20) @OPT SQL_Identifier_Reference table;
	public @S(30) PunctuationSemicolon semicolon;
}
