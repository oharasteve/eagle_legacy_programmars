// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Role_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_CreateRoleStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) SQL_Keyword ROLE = new SQL_Keyword("ROLE");
	public @S(30) SQL_Role_Definition role;
	public @S(40) PunctuationSemicolon semicolon;
}