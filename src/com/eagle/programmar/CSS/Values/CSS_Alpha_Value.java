// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Alpha_Value extends TokenSequence
{
	public @S(10) CSS_Keyword ALPHA = new CSS_Keyword("alpha");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_Keyword OPACITY = new CSS_Keyword("opacity");
	public @S(40) PunctuationEquals equals;
	public @S(50) CSS_Number number;
	public @S(60) PunctuationRightParen rightParen;
}