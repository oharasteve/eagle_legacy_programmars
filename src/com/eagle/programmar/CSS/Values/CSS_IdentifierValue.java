// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.CSS_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_IdentifierValue extends TokenSequence
{
	public @S(10) CSS_Identifier id;
	public @S(20) @OPT CSS_FunctionCall functionCall;
	
	public static class CSS_FunctionCall extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT CSS_FunctionParams params;
		public @S(30) PunctuationRightParen rightParen;
		
		public static class CSS_FunctionParams extends TokenSequence
		{
			public @S(10) CSS_Value value;
			public @S(20) @OPT TokenList<CSS_FunctionAnotherParam> params;
		}

		public static class CSS_FunctionAnotherParam extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) CSS_Value value;
		}
	}
}