// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia;

import com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Julia_Variable extends TokenSequence
{
	public @S(10) @OPT Julia_Punctuation dollar = new Julia_Punctuation("$");
	public @S(20) SeparatedList<Julia_Identifier_Reference,PunctuationPeriod> vars;
	public @S(30) @OPT Julia_Subscript subscript;
	
	public static class Julia_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Julia_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
