// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Eaglish_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Eaglish_Expression expr = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Eaglish_Expression subscr;
	public @S(40) PunctuationRightBracket rightBracket;
}