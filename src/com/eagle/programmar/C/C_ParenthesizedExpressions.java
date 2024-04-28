// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.C;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_ParenthesizedExpressions extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SeparatedList<C_Expression, PunctuationComma> expression;
	public @S(30) PunctuationRightParen rightParen;
}
