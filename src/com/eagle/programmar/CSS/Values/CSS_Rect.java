// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Rect extends TokenSequence
{
	public @S(10) CSS_Keyword RECT = new CSS_Keyword("rect");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_Number num1;
	public @S(40) @OPT PunctuationComma comma1;
	public @S(50) CSS_Number num2;
	public @S(60) @OPT PunctuationComma comma2;
	public @S(70) CSS_Number num3;
	public @S(80) @OPT PunctuationComma comma3;
	public @S(90) CSS_Number num4;
	public @S(100) PunctuationRightParen rightParen;
}