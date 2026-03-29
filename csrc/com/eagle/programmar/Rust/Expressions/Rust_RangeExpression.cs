// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleRange = com.eagle.math.EagleRange;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_Number = com.eagle.programmar.Rust.Terminals.Rust_Number;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Rust_RangeExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression lowExpression = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression lowExpression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_PunctuationChoice dots = new com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice("..", "..=");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE Rust_Expression highExpression = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE TokenList<Rust_RangeModifier> modifiers;
		public  OPT;

		public class Rust_RangeModifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_RangeReverse XXrev;
			public Rust_RangeReverse XXrev;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_RangeStepBy XXstep;
			public Rust_RangeStepBy XXstep;
		}

		public class Rust_RangeReverse : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Keyword REV = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("rev");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
			public  NOSPACE;
		}

		public class Rust_RangeStepBy : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Keyword STEPBY = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("step_by");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE Rust_Expression step;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
			public  NOSPACE;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			int lowValue = interpreter.getIntValue(lowExpression);
			int highValue = 0;
			bool hasHigh = false;

			bool reverse = false;
			int step = 1;
			if (modifiers != null && modifiers.size() > 0)
			{
				foreach (Rust_RangeModifier mod in modifiers._elements)
				{
					AbstractToken which = mod.getWhich();
					if (which is Rust_RangeReverse)
					{
						reverse = !reverse;
					}
					else if (which is Rust_RangeStepBy)
					{
						Rust_RangeStepBy stepBy = (Rust_RangeStepBy) which;
						int by = interpreter.getIntValue(stepBy.step);
						step = step * by;
					}
				}
			}

			if (reverse)
			{
				int temp = highValue;
				highValue = lowValue;
				lowValue = temp;
				step = -step;
			}

			if (highExpression != null && highExpression.isPresent())
			{
				highValue = interpreter.getIntValue(highExpression);
				hasHigh = true;

				if (dots.getValue().Equals("..="))
				{
					highValue++; // Inclusive, 1..5 is 1 to 4; 1..=5 is 1 to 5
				}
			}
			EagleRange range = new EagleRange(lowValue, highValue, hasHigh, step);
			interpreter.pushEagleValue(range);
		}

		public static Rust_RangeExpression generateSubscript(AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc, bool ncMightBeTooBig, AbstractToken source)
		{
			Rust_RangeExpression range = new Rust_RangeExpression();

			switch (whichSC)
			{
			case FIRST_CHAR_IS_ZERO:
				range.lowExpression = (Rust_Expression) sc;
				break;
			case FIRST_CHAR_IS_ONE:
				Rust_Expression one = Rust_Generator.wrapExpression(Rust_Number.generateNumber("1", source));
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				Rust_Expression scMinusOne = Rust_AdditiveExpression.generateAdditive(types, (Rust_Expression) sc, AdditiveEnum.MINUS, one, source);
				range.lowExpression = scMinusOne;
				break;
			}

			range.dots.setValue("..");

			switch (whichEC)
			{
			case GIVEN_EC:
				if (ecOrnc != null)
				{
					switch (whichSC)
					{
					case FIRST_CHAR_IS_ZERO:
						Rust_Expression one = Rust_Generator.wrapExpression(Rust_Number.generateNumber("1", source));
						Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
						Rust_Expression ecPlusOne = Rust_AdditiveExpression.generateAdditive(types, (Rust_Expression) ecOrnc, AdditiveEnum.PLUS, one, source);
						range.highExpression = ecPlusOne;
						break;
					case FIRST_CHAR_IS_ONE:
						range.highExpression = (Rust_Expression) ecOrnc;
						break;
					}
					range.highExpression.setPresent(true);
				}
				break;
			case GIVEN_EC_PLUS_ONE:
				if (ecOrnc != null)
				{
					range.highExpression = (Rust_Expression) ecOrnc;
					range.highExpression.setPresent(true);
				}
				break;
			case GIVEN_NC:
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				Rust_Expression scPlusNc = Rust_AdditiveExpression.generateAdditive(types, range.lowExpression, AdditiveEnum.PLUS, (Rust_Expression) ecOrnc, source);
				range.highExpression = scPlusNc;
				range.highExpression.setPresent(true);
				break;
			case GIVEN_NEITHER:
				break;
			}

			return range;
		}
	}

}
