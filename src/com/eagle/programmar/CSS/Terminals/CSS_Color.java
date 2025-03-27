// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS.Terminals;

import com.eagle.programmar.CSS.Values.CSS_NumericValue;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Color extends TokenChooser
{
	public @CHOICE CSS_KeywordChoice XXcolor = new CSS_KeywordChoice("black", "blue", "green", "red", "white");

	public @CHOICE static class CSS_QualifiedColor extends TokenSequence
	{
		public @S(10) @OPT CSS_KeywordChoice qualifyColor = new CSS_KeywordChoice("dark", "light");
		public @S(20) CSS_KeywordChoice baseColor = new CSS_KeywordChoice("gray", "grey");
	}

	public @CHOICE static class CSS_RGB_Value extends TokenSequence
	{
		public @S(10) CSS_Keyword RGB = new CSS_Keyword("rgb");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number red;
		public @S(40) @OPT PunctuationComma comma1;
		public @S(50) CSS_Number green;
		public @S(60) @OPT PunctuationComma comma2;
		public @S(70) CSS_Number blue;
		public @S(80) PunctuationRightParen rightParen;
	}

	public @CHOICE static class CSS_RGBA_Value extends TokenSequence
	{
		public @S(10) CSS_Keyword RGBA = new CSS_Keyword("rgba");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number red;
		public @S(40) PunctuationComma comma1;
		public @S(50) CSS_Number green;
		public @S(60) PunctuationComma comma2;
		public @S(70) CSS_Number blue;
		public @S(80) PunctuationComma comma3;
		public @S(90) CSS_Number alpha;
		public @S(100) PunctuationRightParen rightParen;
		public @S(110) @OPT CSS_NumericValue percentage;
	}

	public @CHOICE static class CSS_HSL_Value extends TokenSequence
	{
		public @S(10) CSS_Keyword HSL = new CSS_Keyword("hsl");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number hue;
		public @S(40) PunctuationComma comma1;
		public @S(50) CSS_Number saturation;
		public @S(60) @OPT CSS_Punctuation pct1 = new CSS_Punctuation('%');
		public @S(70) PunctuationComma comma2;
		public @S(80) CSS_Number luminosity;
		public @S(90) @OPT CSS_Punctuation pct2 = new CSS_Punctuation('%');
		public @S(100) PunctuationRightParen rightParen;
	}

	public @CHOICE static class CSS_Transparent extends TokenSequence
	{
		public @S(10) CSS_Keyword TRANSPARENT = new CSS_Keyword("transparent");
		public @S(20) CSS_NumericValue percentage;
	}
}
