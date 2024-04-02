// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Ruby_ConditionalAndExpression extends PrecedenceOperator
{
	public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ruby_Punctuation andOperator = new Ruby_Punctuation("&&");
	public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
}
