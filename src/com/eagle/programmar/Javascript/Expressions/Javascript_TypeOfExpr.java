// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_TypeOfExpr extends PrimaryOperator
{
	public @S(10) Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
	public @S(20) Javascript_TypeOfWhat what;

	public static class Javascript_TypeOfWhat extends TokenChooser
	{
		public @CHOICE Javascript_Variable var;

		public @CHOICE static class Javascript_TypeOfParens extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) Javascript_Variable var;
			public @S(30) PunctuationRightParen rightParen;
		}
	}
}
