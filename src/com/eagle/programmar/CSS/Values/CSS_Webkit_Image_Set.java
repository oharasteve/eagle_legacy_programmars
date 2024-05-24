// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.CSS_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Webkit_Image_Set extends TokenSequence
{
	public @S(10) CSS_Keyword WEBKIT_IMAGE_GET = new CSS_Keyword("-webkit-image-set");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_Webkit_Value piece;
	public @S(40) @OPT TokenList<CSS_More_WebkitPieces> morePieces;
	public @S(50) PunctuationRightParen rightParen;

	public static class CSS_Webkit_Value extends TokenSequence
	{
		public @S(10) CSS_Value url;
		public @S(20) CSS_KeywordChoice factor = new CSS_KeywordChoice("1x", "2x", "3x");
	}

	public static class CSS_More_WebkitPieces extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSS_Webkit_Value value;
	}
}