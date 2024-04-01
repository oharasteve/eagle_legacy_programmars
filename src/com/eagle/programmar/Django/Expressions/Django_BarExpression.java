// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_BarExpression extends PrecedenceOperator
{
	public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Django_Punctuation bar = new Django_Punctuation("|");
	public @S(30) Django_BarWhat what;
	
	public static class Django_BarWhat extends TokenChooser
	{
		public @CHOICE Django_KeywordChoice LENGTH = new Django_KeywordChoice(
				"int",
				"length");
		
		public @CHOICE static class Django_BarDefault extends TokenSequence
		{
			public @S(10) Django_Keyword DEFAULT = new Django_Keyword("default");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) Django_Expression expr;
			public @S(40) PunctuationRightParen rightParen;
		}
	}
}
