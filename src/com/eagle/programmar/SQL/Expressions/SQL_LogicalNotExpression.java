// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class SQL_LogicalNotExpression extends PrimaryOperator
{
	public @S(10) SQL_Punctuation notOperator = new SQL_Punctuation('!');
	public @S(20) SQL_Expression expr;
}
