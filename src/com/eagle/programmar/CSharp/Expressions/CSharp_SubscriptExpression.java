// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationLeftBracket leftBracket;
	public @S(30) @NOSPACE CSharp_Expression subscr = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
}
