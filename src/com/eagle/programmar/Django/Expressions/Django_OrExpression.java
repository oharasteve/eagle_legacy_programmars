// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Django_OrExpression extends PrecedenceOperator
{
	public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Django_Keyword AND = new Django_Keyword("and");
	public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
}
