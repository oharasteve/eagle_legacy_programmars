// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.CSS_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Microsoft_Gradient extends TokenSequence
{
	public @S(10) CSS_Keyword PROGID = new CSS_Keyword("progid");
	public @S(20) PunctuationColon colon;
	public @S(30) CSS_Keyword IMAGETRANSFORM = new CSS_Keyword("DXImageTransform");
	public @S(40) PunctuationPeriod dot1;
	public @S(50) CSS_Keyword MICROSOFT = new CSS_Keyword("Microsoft");
	public @S(60) PunctuationPeriod dot2;
	public @S(70) CSS_Keyword GRADIENT = new CSS_Keyword("gradient");
	public @S(80) PunctuationLeftParen leftParen;
	public @S(90) CSS_MS_GradientPiece piece;
	public @S(100) @OPT TokenList<CSS_More_MS_GradientPieces> morePieces;
	public @S(110) PunctuationRightParen rightParen;
	
	public static class CSS_MS_GradientPiece extends TokenSequence
	{
		public @S(10) CSS_KeywordChoice gradPiece = new CSS_KeywordChoice(
				"startColorStr", "endColorStr", "GradientType", "enabled");
		public @S(20) PunctuationEquals equals;
		public @S(30) CSS_Value value;
	}
	
	public static class CSS_More_MS_GradientPieces extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSS_MS_GradientPiece piece;
	}
}