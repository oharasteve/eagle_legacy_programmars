// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class C_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) C_Expression subscr = new C_Expression(this, AllowedPrecedence.ANY);
	public @S(40) PunctuationRightBracket rightBracket;
}
