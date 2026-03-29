// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.FSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Identifier_Reference = com.eagle.programmar.FSharp.Symbols.FSharp_Identifier_Reference;
	using FSharp_Punctuation = com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_SubscriptExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Expression expr = new com.eagle.programmar.FSharp.FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public FSharp_Expression expr = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) FSharp_RangeExpr subscr;
		public FSharp_RangeExpr subscr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public class FSharp_RangeExpr : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST FSharp_RangeExpr_low_high XXlowHigh;
			public FSharp_RangeExpr_low_high XXlowHigh;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_RangeExpr_low XXjustLow;
			public FSharp_RangeExpr_low XXjustLow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_RangeExpr_high XXjustHigh;
			public FSharp_RangeExpr_high XXjustHigh;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST FSharp_RangeJustOne XXjustOne;
			public FSharp_RangeJustOne XXjustOne;
		}

		public class FSharp_RangeExpr_low_high : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Expression low;
			public FSharp_Expression low;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation dotDot = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("..");
			public FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.FSharp_Expression high;
			public FSharp_Expression high;
		}

		public class FSharp_RangeExpr_low : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Expression low;
			public FSharp_Expression low;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation dotDot = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("..");
			public FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
		}

		public class FSharp_RangeExpr_high : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation dotDot = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("..");
			public FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Expression high;
			public FSharp_Expression high;
		}

		public class FSharp_RangeJustOne : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Expression subscr;
			public FSharp_Expression subscr;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (subscr.getWhich() is FSharp_RangeJustOne)
			{
				FSharp_RangeJustOne justOne = (FSharp_RangeJustOne) subscr.getWhich();
				EagleValue val = interpreter.getEagleValue(expr);
				if (val.isArray())
				{
					EagleArray array = (EagleArray) val;
					int sub = interpreter.getIntValue(justOne.subscr);
					interpreter.pushEagleValue(array.getValue(sub));
				}
				else
				{
					throw new Exception("Unable to use subscript for " + expr);
				}
			}
			else
			{
				string str = interpreter.getStrValue(expr);
				int sc = 0;
				int ec = str.Length;
				if (subscr.getWhich() is FSharp_RangeExpr_low_high)
				{
					FSharp_RangeExpr_low_high range = (FSharp_RangeExpr_low_high) subscr.getWhich();
					sc = interpreter.getIntValue(range.low);
					ec = interpreter.getIntValue(range.high) + 1;
				}
				else if (subscr.getWhich() is FSharp_RangeExpr_low)
				{
					FSharp_RangeExpr_low range = (FSharp_RangeExpr_low) subscr.getWhich();
					sc = interpreter.getIntValue(range.low);
				}
				else if (subscr.getWhich() is FSharp_RangeExpr_high)
				{
					FSharp_RangeExpr_high range = (FSharp_RangeExpr_high) subscr.getWhich();
					ec = interpreter.getIntValue(range.high) + 1;
				}

				interpreter.pushStr(str.Substring(sc, ec - sc));
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken whichSubscr = subscr.getWhich();
			if (whichSubscr is FSharp_RangeJustOne)
			{
				if (expr.getWhich() is FSharp_VariableExpression)
				{
					FSharp_VariableExpression varExpr = (FSharp_VariableExpression) expr.getWhich();
					FSharp_Identifier_Reference id = varExpr.variable.id;
					string varName = id.getValue();
					FSharp_RangeJustOne justOne = (FSharp_RangeJustOne) whichSubscr;
					AbstractExpression subExpr = transformer.transformExpression(generator, justOne.subscr);
					return generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
				}
			}
			else
			{
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				if (whichSubscr is FSharp_RangeExpr_low_high)
				{
					FSharp_RangeExpr_low_high range = (FSharp_RangeExpr_low_high) whichSubscr;
					AbstractExpression scExpr = transformer.transformExpression(generator, range.low);
					AbstractExpression ecExpr = transformer.transformExpression(generator, range.high);
					return generator.newSubstringFunction(newExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_EC, ecExpr, false, expr);
				}
				else if (whichSubscr is FSharp_RangeExpr_low)
				{
					FSharp_RangeExpr_low range = (FSharp_RangeExpr_low) whichSubscr;
					AbstractExpression scExpr = transformer.transformExpression(generator, range.low);
					return generator.newSubstringFunction(newExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_NEITHER, null, false, expr);
				}
				else if (whichSubscr is FSharp_RangeExpr_high)
				{
					FSharp_RangeExpr_high range = (FSharp_RangeExpr_high) whichSubscr;
					AbstractExpression scExpr = generator.newNumberExpression("0", range);
					AbstractExpression ecExpr = transformer.transformExpression(generator, range.high);
					return generator.newSubstringFunction(newExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_EC, ecExpr, false, expr);
				}
			}

			throw new Exception("Unable to handle subscript");
		}
	}
}
