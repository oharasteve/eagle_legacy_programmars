// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.programmar.JSON.Terminals.JSON_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class JSON_Object extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT TokenList<JSON_Comment> comments1;
	public @S(30) @OPT JSON_Element element;
	public @S(40) @OPT TokenList<JSON_MoreObjects> moreElements;
	public @S(50) @OPT TokenList<JSON_Comment> comments2;
	public @S(60) PunctuationRightBracket rightBracket;
	
	public static class JSON_MoreObjects extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<JSON_Comment> comments;
		public @S(30) JSON_Element element;
	}
}
