// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell;

import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_Type extends TokenChooser
{
	public @CHOICE static class Haskell_IO_type extends TokenSequence
	{
		public @S(10) Haskell_Keyword IO = new Haskell_Keyword("IO");
		public @S(20) Haskell_Type type;
	}
	
	public @CHOICE Haskell_KeywordChoice XXprimitive = new Haskell_KeywordChoice(
			"Bool",
			"Int", "Integer",
			"String");
	
	public @CHOICE static class Haskell_TypeTuple extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Haskell_Type, PunctuationComma> types;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class Haskell_TypeArray extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket left;
		public @S(20) Haskell_Type type;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
