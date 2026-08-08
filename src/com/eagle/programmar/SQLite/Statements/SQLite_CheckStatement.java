// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQLite_CheckStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CHECK = new SQL_Keyword(".check");
	public @S(20) SQLite_CheckWhat what;
	
	public static class SQLite_CheckWhat extends TokenChooser
	{
		public @CHOICE SQL_Literal XXliteral;
		public @CHOICE SQL_Keyword XXNULL = new SQL_Keyword("NULL");
	}
}
