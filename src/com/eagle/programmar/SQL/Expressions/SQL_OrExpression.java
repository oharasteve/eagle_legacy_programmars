// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class SQL_OrExpression extends PrecedenceOperator
{
	public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) SQL_Keyword OR = new SQL_Keyword("OR");
	public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
}
