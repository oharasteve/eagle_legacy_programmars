// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Django_NotExpression extends PrimaryOperator
{
	public @S(10) Django_Keyword NOT = new Django_Keyword("not");
	public @S(20) Django_Expression expr;
}
