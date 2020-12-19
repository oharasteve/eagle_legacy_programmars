// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_Subscript extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
	public @S(20) @NOSPACE Java_Expression expr;
	public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
}
