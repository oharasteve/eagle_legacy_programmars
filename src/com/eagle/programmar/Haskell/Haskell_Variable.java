// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenSequence.S;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Haskell_Variable extends TokenSequence
{
	public @S(10) Haskell_Identifier_Reference id;
	public @S(20) @OPT Haskell_Subscript subscript;

	public static class Haskell_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Haskell_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
