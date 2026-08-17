// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQLite_ImposterStatement extends TokenSequence
{
	public @S(10) SQL_Keyword IMPOSTER = new SQL_Keyword(".imposter");
	public @S(20) SQL_Identifier_Reference idRef;
	public @S(30) SQL_Declare_Definition idDef;
}
