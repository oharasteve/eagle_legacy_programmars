// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression.FSharp_RangeExpr.FSharp_RangeExpr_high;
import com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression.FSharp_RangeExpr.FSharp_RangeExpr_low;
import com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression.FSharp_RangeExpr.FSharp_RangeExpr_low_high;
import com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression.FSharp_RangeExpr.FSharp_RangeJustOne;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class FSharp_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) FSharp_Expression expr = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) PunctuationLeftBracket leftBracket;
	public @S(40) FSharp_RangeExpr subscr;
	public @S(50) PunctuationRightBracket rightBracket;

	public static class FSharp_RangeExpr extends TokenChooser
	{
		public @FIRST static class FSharp_RangeExpr_low_high extends TokenSequence
		{
			public @S(10) FSharp_Expression low;
			public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
			public @S(30) FSharp_Expression high;
		}

		public @CHOICE static class FSharp_RangeExpr_low extends TokenSequence
		{
			public @S(10) FSharp_Expression low;
			public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
		}

		public @CHOICE static class FSharp_RangeExpr_high extends TokenSequence
		{
			public @S(10) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
			public @S(20) FSharp_Expression high;
		}

		public @LAST static class FSharp_RangeJustOne extends TokenSequence
		{
			public @S(10) FSharp_Expression subscr;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscr.getWhich() instanceof FSharp_RangeJustOne)
		{
			FSharp_RangeJustOne justOne = (FSharp_RangeJustOne) subscr.getWhich();
			EagleValue val = interpreter.getEagleValue(expr);
			if (val.isArray())
			{
				EagleArray array = (EagleArray) val;
				int sub = interpreter.getIntValue(justOne.subscr);
				interpreter.pushEagleValue(array.getValue(sub));
			}
			else throw new RuntimeException("Unable to use subscript for " + expr);
		}
		else
		{
			String str = interpreter.getStrValue(expr);
			int sc = 0;
			int ec = str.length();
			if (subscr.getWhich() instanceof FSharp_RangeExpr_low_high)
			{
				FSharp_RangeExpr_low_high range = (FSharp_RangeExpr_low_high) subscr.getWhich();
				sc = interpreter.getIntValue(range.low);
				ec = interpreter.getIntValue(range.high) + 1;
			}
			else if (subscr.getWhich() instanceof FSharp_RangeExpr_low)
			{
				FSharp_RangeExpr_low range = (FSharp_RangeExpr_low) subscr.getWhich();
				sc = interpreter.getIntValue(range.low);
			}
			else if (subscr.getWhich() instanceof FSharp_RangeExpr_high)
			{
				FSharp_RangeExpr_high range = (FSharp_RangeExpr_high) subscr.getWhich();
				ec = interpreter.getIntValue(range.high) + 1;
			}
			
			interpreter.pushStr(str.substring(sc, ec));
		}
	}
}