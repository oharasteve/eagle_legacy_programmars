// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.TokenSequence;

public class SQLite_TestCaseStatement extends TokenSequence
{
	public @S(10) SQL_Keyword TESTCASE = new SQL_Keyword(".testcase");
	public @S(20) @OPT SQL_Number number;
	public @S(30) @OPT SQL_Keyword SETUP = new SQL_Keyword("setup");
}
