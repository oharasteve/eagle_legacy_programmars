// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class SQL_DelimiterStatement extends TokenSequence
{
	public @S(10) SQL_Keyword DELIMITER = new SQL_Keyword("DELIMITER");
	public @S(20) SQL_PunctuationChoice what = new SQL_PunctuationChoice(";", "//");
}
