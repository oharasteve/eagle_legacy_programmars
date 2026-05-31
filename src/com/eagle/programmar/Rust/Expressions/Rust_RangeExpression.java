// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Functions.Rust_LenMethod;
import com.eagle.programmar.Rust.Functions.Rust_MinMaxMethod;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.MinMaxEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Rust_RangeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression lowExpression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT @NOSPACE Rust_PunctuationChoice dots = new Rust_PunctuationChoice("..", "..=");
	public @S(30) @OPT @NOSPACE Rust_Expression highExpression = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int lowValue = interpreter.getIntValue(lowExpression);
		int highValue = 0;
		boolean hasHigh = false;
		
		if (highExpression != null && highExpression.isPresent())
		{
			highValue = interpreter.getIntValue(highExpression);
			hasHigh = true;
			
			if (dots.getValue().equals("..="))
			{
				highValue++;	// Inclusive, 1..5 is 1 to 4; 1..=5 is 1 to 5
			}
		}
		EagleRange range = new EagleRange(lowValue, highValue, hasHigh, 1);
		interpreter.pushEagleValue(range);
	}
	
	public static Rust_RangeExpression generateSubscript(Rust_Expression theExpr, Rust_Expression sc, SubstringSCEnum whichSC,
			SubstringECEnum whichEC, Rust_Expression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		Rust_RangeExpression range = new Rust_RangeExpression();
		Rust_Type usize = Rust_Type.newPrimitiveType("usize");
		
		switch (whichSC)
		{
		case FIRST_CHAR_IS_ZERO:
			range.lowExpression = sc;
			break;
		case FIRST_CHAR_IS_ONE:
			Rust_Expression one = Rust_Generator.wrapExpression(Rust_Number.generateNumber("1", source));
			Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
			Rust_Expression scMinusOne = Rust_AdditiveExpression.generateAdditive(types,
					sc, AdditiveEnum.MINUS, one, source);
			range.lowExpression = scMinusOne;
			break;
		}
		
		range.lowExpression = Rust_AsExpression.generateAsExpr(range.lowExpression, usize, source);
		if (whichEC != SubstringECEnum.JUST_ONE)
		{
			range.dots.setValue("..");
		}
		range.highExpression = null;
		
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
							ecOrnc, AdditiveEnum.PLUS, one, source);
					range.highExpression = ecPlusOne;
					break;
				case FIRST_CHAR_IS_ONE:
					range.highExpression = ecOrnc;
					break;
				default:
					throw new RuntimeException("Unexpected sc: " + whichSC.toString());
				}
			}
			break;
		case GIVEN_EC_PLUS_ONE:
			if (ecOrnc != null)
			{
				range.highExpression = ecOrnc;
			}
			break;
		case GIVEN_NC:
			Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
			Rust_Expression scPlusNc = Rust_AdditiveExpression.generateAdditive(types,
					range.lowExpression, AdditiveEnum.PLUS, ecOrnc, source);
			range.highExpression = scPlusNc;
			break;
		case JUST_ONE:
			break;
		case TO_END:
			break;
		default:
			throw new RuntimeException("Unexpected ec: " + whichEC.toString());
		}
		
		if (range.highExpression != null)
		{
			range.highExpression.setPresent(true);
			range.highExpression = Rust_AsExpression.generateAsExpr(range.highExpression, usize, source);
			range.highExpression.setPresent(true);		// Yes, you need to set it again and again ...
			
			// Need to handle ncMightBeTooBig. Can't let ec go past len(left)
			if (ncMightBeTooBig)
			{
				// (9999 as usize).min(abc.len())
				Rust_Expression paren = Rust_ParenthesizedExpression.generateParentheses(range.highExpression, null);
				Rust_Expression len = Rust_LenMethod.generateLengthUsize(theExpr);
				Rust_Expression min = Rust_MinMaxMethod.generateMinMax2(MinMaxEnum.MIN, paren, len, null);
				range.highExpression = Rust_ParenthesizedExpression.generateParentheses(min, source);
				range.highExpression.setPresent(true);
			}
		}

		return range;
	}
}
