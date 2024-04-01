// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Django_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Django_Expression expr = new Django_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Django_Expression subscr;
	public @S(40) PunctuationRightBracket rightBracket;
}
