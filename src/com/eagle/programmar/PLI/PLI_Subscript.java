// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.PLI;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class PLI_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT SeparatedList<PLI_ExpressionOrStar, PunctuationComma> args;
	public @S(30) PunctuationRightParen rightParen;

	public static class PLI_ExpressionOrStar extends TokenChooser
	{
		public @CHOICE PLI_Expression expr;
		public @CHOICE PunctuationStar star;
	}
}
