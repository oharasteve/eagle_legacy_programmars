// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class FSharp_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) FSharp_Expression expr = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) FSharp_Punctuation leftDotBracket = new FSharp_Punctuation(".[");
	public @S(30) FSharp_RangeExpr subscr;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class FSharp_RangeExpr extends TokenChooser
	{
		public @FIRST static class FSharp_RangeExpr_low_high extends TokenSequence
		{
			public @S(10) FSharp_Expression low;
			public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
			public @S(30) @OPT FSharp_Expression high;
		}

		public @CHOICE static class FSharp_RangeExpr_low extends TokenSequence
		{
			public @S(10) FSharp_Expression low;
			public @S(20) @OPT FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
		}

		public @CHOICE static class FSharp_RangeExpr_high extends TokenSequence
		{
			public @S(10) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
			public @S(20) @OPT FSharp_Expression high;
		}
	}
}