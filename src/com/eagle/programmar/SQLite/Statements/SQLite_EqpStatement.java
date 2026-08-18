// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 18, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQLite_EqpStatement extends TokenSequence
{
	public @S(10) SQL_Keyword EQP = new SQL_Keyword(".eqp");
	public @S(20) SQL_Keyword ON = new SQL_Keyword("on");
}
