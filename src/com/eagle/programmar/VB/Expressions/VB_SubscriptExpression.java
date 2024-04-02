// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class VB_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) VB_Expression subscr = new VB_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) PunctuationRightBracket rightBracket;
}
