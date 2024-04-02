// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Perl_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Perl_Expression subscr = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) PunctuationRightBracket rightBracket;
}
