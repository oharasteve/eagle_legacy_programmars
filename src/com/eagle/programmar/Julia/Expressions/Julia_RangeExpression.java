// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Julia_RangeExpression extends PrecedenceOperator
{
	public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationColon colon;
	public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) @OPT Julia_Range_Increment increment;
	
	public static class Julia_Range_Increment extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Julia_Expression expr;
	}
}
