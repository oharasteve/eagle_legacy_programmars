// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Cubic_Bezier extends TokenSequence
{
	public @S(10) CSS_Keyword CUBIC_BEZIER = new CSS_Keyword("cubic-bezier");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_Number number1;
	public @S(40) PunctuationComma comma1;
	public @S(50) CSS_Number number2;
	public @S(60) PunctuationComma comma2;
	public @S(70) CSS_Number number3;
	public @S(80) PunctuationComma comma3;
	public @S(90) CSS_Number number4;
	public @S(100) PunctuationRightParen rightParen;
}