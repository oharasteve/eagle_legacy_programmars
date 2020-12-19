// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Perl_Subscript extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
	public @S(20) @NOSPACE Perl_Expression expr;
	public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
}
