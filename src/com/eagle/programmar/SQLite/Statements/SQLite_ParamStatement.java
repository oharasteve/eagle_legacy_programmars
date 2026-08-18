// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQLite_ParamStatement extends TokenSequence
{
	public @S(10) SQL_Keyword PARAM = new SQL_Keyword(".param");
	public @S(20) SQL_Keyword SET = new SQL_Keyword("set");
	public @S(30) SQL_Punctuation dollar = new SQL_Punctuation('$');
	public @S(40) SQL_Identifier_Reference id;
	public @S(50) SQL_Number number;
}
