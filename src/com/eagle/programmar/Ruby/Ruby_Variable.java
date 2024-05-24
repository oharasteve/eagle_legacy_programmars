// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby;

import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Ruby_Variable extends TokenSequence
{
	public @S(10) @OPT Ruby_Punctuation dollar = new Ruby_Punctuation("$");
	public @S(20) SeparatedList<Ruby_Identifier_Reference, PunctuationPeriod> vars;
	public @S(30) @OPT Ruby_Subscript subscript;

	public static class Ruby_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Ruby_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
