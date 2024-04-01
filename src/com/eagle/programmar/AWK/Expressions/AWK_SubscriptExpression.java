// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class AWK_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) AWK_Expression subscr = new AWK_Expression();
	public @S(40) PunctuationRightBracket rightBracket;
}
