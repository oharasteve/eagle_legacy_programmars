// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Rexx_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Rexx_Expression expr = new Rexx_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Rexx_Expression subscr = new Rexx_Expression(this, AllowedPrecedence.HIGHER);
}
