// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class FSharp_Parens extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) FSharp_Expression expression;
	public @S(30) PunctuationRightParen rightParen;
}
