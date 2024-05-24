// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Go_Variable extends TokenSequence
{
	public @S(10) SeparatedList<Go_Identifier_Reference, PunctuationPeriod> vars;
	public @S(20) @OPT Go_Subscript subscript;

	public static class Go_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Go_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
