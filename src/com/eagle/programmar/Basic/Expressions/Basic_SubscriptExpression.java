// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Expressions;

import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Basic_Expression expr = new Basic_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Basic_Expression subscr = new Basic_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) PunctuationRightParen rightParen;
}
