// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class SQL_Builtin extends PrimaryOperator
{
	public @S(10) SQL_KeywordChoice SYSTIMESTAMP = new SQL_KeywordChoice(
			"FALSE",
			"NULL",
			"SYSTIMESTAMP",
			"TRUE");
}
