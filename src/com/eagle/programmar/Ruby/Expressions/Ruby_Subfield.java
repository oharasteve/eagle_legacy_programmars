// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Ruby_Subfield extends PrecedenceOperator
{
	public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
}
