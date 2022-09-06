// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_View_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;

public class SQL_CreateViewStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) @OPT SQL_Keyword OR = new SQL_Keyword("OR");
	public @S(30) @OPT SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
	public @S(40) SQL_Keyword VIEW = new SQL_Keyword("VIEW");
	public @S(50) SQL_View_Definition view;
	public @S(60) SQL_Keyword AS = new SQL_Keyword("AS");
	public @S(70) SQL_SelectStatement select;
}