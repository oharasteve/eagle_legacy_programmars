// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Java_Expression expr = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Java_Expression subscr;
	public @S(40) PunctuationRightBracket rightBracket;
}