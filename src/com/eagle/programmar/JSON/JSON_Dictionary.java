// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.Terminals.JSON_Comment;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class JSON_Dictionary extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT JSON_DictEntries entries;
	public @S(30) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(40) PunctuationRightBrace rightBrace;
	
	public static class JSON_DictEntries extends TokenSequence
	{
		public @S(10) JSON_DictEntry entry;
		public @S(20) @OPT TokenList<JSON_AnotherEntry> more;
		
		public static class JSON_AnotherEntry extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT JSON_Comment comment;
			public @S(30) JSON_DictEntry entry;
		}
	}
	
	public static class JSON_DictEntry extends TokenSequence
	{
		public @S(10) JSON_Literal name;
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT JSON_Comment comment;
		public @S(40) JSON_Element value;
	}
}
