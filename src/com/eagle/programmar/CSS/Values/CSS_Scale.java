// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Scale extends TokenSequence
{
	public @S(10) CSS_Keyword SCALE = new CSS_Keyword("scale");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_Number number1;
	public @S(40) PunctuationComma comma2;
	public @S(50) CSS_Number number2;
	public @S(60) PunctuationRightParen rightParen;
}