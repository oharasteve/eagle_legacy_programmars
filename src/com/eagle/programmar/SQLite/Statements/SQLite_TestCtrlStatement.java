// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQLite_TestCtrlStatement extends TokenSequence
{
	public @S(10) SQL_Keyword TESTCTRL = new SQL_Keyword(".testctrl");
	public @S(20) @OPT SQLite_TestCtrlOption option;
	
	public static class SQLite_TestCtrlOption extends TokenSequence
	{
		public @S(10) SQL_Keyword OPT = new SQL_Keyword("opt");
		public @S(20) SQL_Keyword STAT4 = new SQL_Keyword("-Stat4");
	}
}
