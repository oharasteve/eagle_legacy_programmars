// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Scala_Type extends TokenChooser
{
	public @CHOICE Scala_KeywordChoice type = new Scala_KeywordChoice(
			"bool",
			"Boolean",
			"int",
			"string");
	
	public static @CHOICE class Scala_TypeArray extends TokenSequence
	{
		public @S(10) Scala_Keyword LIST = new Scala_Keyword("List");
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Scala_Type subtype;
		public @S(40) PunctuationRightBracket rightBracket;
	}
}
