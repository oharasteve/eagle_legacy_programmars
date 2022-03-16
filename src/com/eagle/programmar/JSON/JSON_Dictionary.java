// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.JSON_Program.JSON_Element;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class JSON_Dictionary extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT SeparatedList<JSON_DictEntry,PunctuationComma> entries;
	public @S(30) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(40) PunctuationRightBrace rightBrace;
	
	public static class JSON_DictEntry extends TokenSequence
	{
		public @S(10) JSON_Literal name;
		public @S(20) PunctuationColon colon;
		public @S(30) JSON_Element value;
	}
}
