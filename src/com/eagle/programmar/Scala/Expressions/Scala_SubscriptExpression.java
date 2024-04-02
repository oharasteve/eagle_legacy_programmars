// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Scala_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Scala_Expression expr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Scala_Expression subscr1;
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT Scala_Expression subscr2;
	public @S(60) PunctuationRightBracket rightBracket;
}
