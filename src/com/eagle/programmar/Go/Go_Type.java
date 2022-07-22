// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Go_Type extends TokenSequence
{
	public @S(10) @OPT Go_TypeArray array;
	public @S(20) @OPT PunctuationStar star;
	public @S(30) Go_TypeWhat what;
	
	public static class Go_TypeWhat extends TokenChooser
	{
		public @CHOICE Go_KeywordChoice primitive = new Go_KeywordChoice(
				"bool",
				"int",
				"string");
		
		public @LAST static class Go_UserType extends TokenSequence
		{
			public @S(10) SeparatedList<Go_Identifier_Reference,PunctuationPeriod> ids;
		}
	}
	
	public static class Go_TypeArray extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}
}
