// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Base64;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_URL_Value extends TokenSequence
{
	public @S(10) CSS_Keyword URL = new CSS_Keyword("url");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_File file;
	public @S(40) PunctuationRightParen rightParen;

	public static class CSS_File extends TokenChooser
	{
		public @CHOICE CSS_FileName fileName;

		public @CHOICE static class CSS_FileInline extends TokenSequence
		{
			public @S(10) CSS_Keyword DATA = new CSS_Keyword("data");
			public @S(20) PunctuationColon colon;
			public @S(30) CSS_Keyword IMAGE = new CSS_Keyword("image");
			public @S(40) PunctuationSlash slash;
			public @S(50) CSS_Keyword PNG = new CSS_Keyword("png");
			public @S(60) PunctuationSemicolon semicolon;
			public @S(70) CSS_Keyword BASE64 = new CSS_Keyword("base64");
			public @S(80) PunctuationComma comma;
			public @S(90) CSS_Base64 base64;
		}
	}
}