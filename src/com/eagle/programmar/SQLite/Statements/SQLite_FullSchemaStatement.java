// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 18, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQLite_FullSchemaStatement extends TokenSequence
{
	public @S(10) SQL_Keyword FULLSCHEMA = new SQL_Keyword(".fullschema");
}
