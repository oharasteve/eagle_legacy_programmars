package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Go_Variable extends TokenSequence
{
	public @S(10) Go_Identifier_Reference var;
	public @S(30) @OPT Go_Subscript subscript;
	
	public static class Go_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Go_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
