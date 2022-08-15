// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Scala_Variable extends TokenSequence
{
	public @S(10) SeparatedList<Scala_Identifier_Reference,PunctuationPeriod> vars;
	public @S(20) @OPT Scala_Subscript subscript;
	
	public static class Scala_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Scala_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
