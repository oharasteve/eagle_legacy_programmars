// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Javascript_SimpleArray extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Javascript_Expression expr;
	public @S(30) @OPT TokenList<Javascript_MoreArray> more;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class Javascript_MoreArray extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Javascript_Expression expr;
	}
}
