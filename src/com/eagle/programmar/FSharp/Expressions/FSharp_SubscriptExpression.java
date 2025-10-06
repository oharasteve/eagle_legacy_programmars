// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Symbols.FSharp_Identifier_Reference;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class FSharp_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) FSharp_Expression expr = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) PunctuationLeftBracket leftBracket;
	public @S(40) FSharp_RangeExpr subscr;
	public @S(50) PunctuationRightBracket rightBracket;

	public static class FSharp_RangeExpr extends TokenChooser
	{
		public @FIRST FSharp_RangeExpr_low_high XXlowHigh;
		public @CHOICE FSharp_RangeExpr_low XXjustLow;
		public @CHOICE FSharp_RangeExpr_high XXjustHigh;
		public @LAST FSharp_RangeJustOne XXjustOne;
	}
	
	public static class FSharp_RangeExpr_low_high extends TokenSequence
	{
		public @S(10) FSharp_Expression low;
		public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
		public @S(30) FSharp_Expression high;
	}

	public static class FSharp_RangeExpr_low extends TokenSequence
	{
		public @S(10) FSharp_Expression low;
		public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
	}

	public static class FSharp_RangeExpr_high extends TokenSequence
	{
		public @S(10) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
		public @S(20) FSharp_Expression high;
	}

	public static class FSharp_RangeJustOne extends TokenSequence
	{
		public @S(10) FSharp_Expression subscr;
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractToken whichSubscr = subscr.getWhich();
		if (whichSubscr instanceof FSharp_RangeJustOne)
		{
			if (expr.getWhich() instanceof FSharp_VariableExpression)
			{
				FSharp_VariableExpression varExpr = (FSharp_VariableExpression) expr.getWhich();
				FSharp_Identifier_Reference id = varExpr.variable.id;
				String varName = id.getValue();
				FSharp_RangeJustOne justOne = (FSharp_RangeJustOne) whichSubscr;
				AbstractExpression subExpr = transformer.transformExpression(generator, justOne.subscr);
				return generator.newVariableExpression(varName, SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
			}
		}
		else
		{
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			if (whichSubscr instanceof FSharp_RangeExpr_low_high)
			{
				FSharp_RangeExpr_low_high range = (FSharp_RangeExpr_low_high) whichSubscr;
				AbstractExpression scExpr = transformer.transformExpression(generator, range.low);
				AbstractExpression ecExpr = transformer.transformExpression(generator, range.high);
				return generator.newSubstringFunction(newExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
						SubstringECEnum.GIVEN_EC, ecExpr, false, expr);
			}
			else if (whichSubscr instanceof FSharp_RangeExpr_low)
			{
				FSharp_RangeExpr_low range = (FSharp_RangeExpr_low) whichSubscr;
				AbstractExpression scExpr = transformer.transformExpression(generator, range.low);
				return generator.newSubstringFunction(newExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
						SubstringECEnum.GIVEN_NEITHER, null, false, expr);
			}
			else if (whichSubscr instanceof FSharp_RangeExpr_high)
			{
				FSharp_RangeExpr_high range = (FSharp_RangeExpr_high) whichSubscr;
				AbstractExpression scExpr = generator.newNumberExpression("0", range);
				AbstractExpression ecExpr = transformer.transformExpression(generator, range.high);
				return generator.newSubstringFunction(newExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
						SubstringECEnum.GIVEN_EC, ecExpr, false, expr);
			}
		}

		throw new RuntimeException("Unable to handle subscript");
	}
}