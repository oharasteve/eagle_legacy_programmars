// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQLite_IntCkStatement extends TokenSequence
{
	public @S(10) SQL_Keyword INTCK = new SQL_Keyword(".intck");
	public @S(20) SQL_Keyword REGISTER = new SQL_Keyword("register");
}
