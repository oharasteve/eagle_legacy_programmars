// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Rust_RangeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression lowExpression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE Rust_PunctuationChoice dots = new Rust_PunctuationChoice("..", "..=");
	public @S(30) @OPT @NOSPACE Rust_Expression highExpression = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) @OPT @NOSPACE TokenList<Rust_RangeModifier> modifiers;
	
	public static class Rust_RangeModifier extends TokenChooser
	{
		public @CHOICE Rust_RangeReverse XXrev;
		public @CHOICE Rust_RangeStepBy XXstep;
	}

	public static class Rust_RangeReverse extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) @NOSPACE Rust_Keyword REV = new Rust_Keyword("rev");
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static class Rust_RangeStepBy extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) @NOSPACE Rust_Keyword STEPBY = new Rust_Keyword("step_by");
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @NOSPACE Rust_Expression step;
		public @S(50) @NOSPACE PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int lowValue = interpreter.getIntValue(lowExpression);
		int highValue = 0;
		boolean hasHigh = false;
		
		boolean reverse = false;
		int step = 1;
		if (modifiers != null && modifiers.size() > 0)
		{
			for (Rust_RangeModifier mod : modifiers._elements)
			{
				AbstractToken which = mod.getWhich();
				if (which instanceof Rust_RangeReverse)
				{
					reverse = ! reverse;
				}
				else if (which instanceof Rust_RangeStepBy)
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
			
			if (dots.getValue().equals("..="))
			{
				highValue++;	// Inclusive, 1..5 is 1 to 4; 1..=5 is 1 to 5
			}
		}
		EagleRange range = new EagleRange(lowValue, highValue, hasHigh, step);
		interpreter.pushEagleValue(range);
	}
	
	public static Rust_RangeExpression generateSubscript(AbstractExpression sc, SubstringSCEnum whichSC,
			SubstringECEnum whichEC, AbstractExpression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
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
			Rust_Expression scMinusOne = Rust_AdditiveExpression.generateAdditive(types,
					(Rust_Expression) sc, AdditiveEnum.MINUS, one, source);
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
					Rust_Expression ecPlusOne = Rust_AdditiveExpression.generateAdditive(types,
							(Rust_Expression) ecOrnc, AdditiveEnum.PLUS, one, source);
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
			Rust_Expression scPlusNc = Rust_AdditiveExpression.generateAdditive(types,
					range.lowExpression, AdditiveEnum.PLUS, (Rust_Expression) ecOrnc, source);
			range.highExpression = scPlusNc;
			range.highExpression.setPresent(true);
			break;
		case GIVEN_NEITHER:
			break;
		}
		
		return range;
	}
}
