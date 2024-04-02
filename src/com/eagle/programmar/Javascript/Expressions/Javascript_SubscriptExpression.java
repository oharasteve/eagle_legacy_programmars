// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Javascript_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Javascript_Expression subscr = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) PunctuationRightBracket rightBracket;
}
