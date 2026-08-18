// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 18, 2026

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class SQL_ExistsExpression extends PrimaryOperator
{
	public @S(10) SQL_Keyword EXISTS = new SQL_Keyword("EXISTS");
	public @S(20) SQL_Expression expr;
}
