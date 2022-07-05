// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Go_Type extends TokenSequence
{
	public @S(10) @OPT Go_TypeArray array;
	public @S(20) Go_KeywordChoice type = new Go_KeywordChoice("string");
	
	public static class Go_TypeArray extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}
}
