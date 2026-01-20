// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleRange;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class Rust_RangeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression lowExpression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice dots = new Rust_PunctuationChoice("..", "..=");
	public @S(30) @OPT Rust_Expression highExpression = new Rust_Expression(this, AllowedPrecedence.HIGHER);

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
			Rust_Number num = new Rust_Number();
			Rust_Expression one = Rust_Generator.wrapExpression(num.generateNumber("1", source));
			Rust_AdditiveExpression addExpr = new Rust_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Rust_Expression scMinusOne = addExpr.generateAdditive(types,
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
					Rust_Number num = new Rust_Number();
					Rust_Expression one = Rust_Generator.wrapExpression(num.generateNumber("1", source));
					Rust_AdditiveExpression addExpr = new Rust_AdditiveExpression();
					Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
					Rust_Expression ecPlusOne = addExpr.generateAdditive(types,
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
			Rust_AdditiveExpression addExpr = new Rust_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Rust_Expression scPlusNc = addExpr.generateAdditive(types,
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
