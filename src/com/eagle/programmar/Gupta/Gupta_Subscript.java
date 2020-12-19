// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Gupta_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Gupta_Expression expr;
	public @S(30) PunctuationRightParen rightParen;
}
