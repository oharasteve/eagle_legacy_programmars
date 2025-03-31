// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQL_CallStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CALL = new SQL_Keyword("CALL");
	public @S(20) SQL_BuiltinFunction func;
}
