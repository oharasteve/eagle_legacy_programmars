// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2025

package com.eagle.programmar.BNF.Expressions;

import com.eagle.programmar.BNF.BNF_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class BNF_Optional extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) BNF_Expression expression;
	public @S(30) PunctuationRightBracket rightBracket;
}