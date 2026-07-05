// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Methods;

import com.eagle.generate.AdditiveEnum;
import com.eagle.generate.MinMaxEnum;
import com.eagle.generate.SubstringECEnum;
import com.eagle.generate.SubstringSCEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Functions.Java_MathMinMaxFunc;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_SubstringMethod extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword SUBSTRING = new Java_Keyword("substring");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Java_Expression scExpr;
	public @S(60) @OPT @NOSPACE PunctuationComma comma;
	public @S(70) @OPT Java_Expression ecExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		int sc = interpreter.getIntValue(scExpr);
		if (ecExpr != null && ecExpr.isPresent())
		{
			int ec = interpreter.getIntValue(ecExpr);
			interpreter.pushStr(leftStr.substring(sc, ec));
		}
		else
		{
			interpreter.pushStr(leftStr.substring(sc));
		}
	}

	public static Java_Expression generateExpression(Java_Expression theExpr,
			Java_Expression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			Java_Expression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		Java_SubstringMethod expr = new Java_SubstringMethod();
		expr.dot = new PunctuationPeriod();
		expr.left = theExpr;
		expr.leftParen = new PunctuationLeftParen();
		expr.rightParen = new PunctuationRightParen();

		switch (whichSC)
		{
		case FIRST_CHAR_IS_ZERO:
			expr.scExpr = sc;
			break;
		case FIRST_CHAR_IS_ONE:
			Java_Expression one = Java_Number.generateNumberExpression("1", source);
			Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
			Java_Expression scMinusOne = Java_AdditiveExpression.generateAdditive(types, sc,
					AdditiveEnum.MINUS, one, source);
			expr.scExpr = scMinusOne;
			break;
		}

		switch (whichEC)
		{
		case GIVEN_EC:
			if (ecOrnc != null)
			{
				expr.comma = new PunctuationComma();
				expr.comma.setPresent(true);
				switch (whichSC)
				{
				case FIRST_CHAR_IS_ZERO:
					Java_Expression one = Java_Number.generateNumberExpression("1", source);
					Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
					Java_Expression ecPlusOne = Java_AdditiveExpression.generateAdditive(types, ecOrnc,
							AdditiveEnum.PLUS, one, source);
					expr.ecExpr = ecPlusOne;
					break;
				case FIRST_CHAR_IS_ONE:
					expr.ecExpr = ecOrnc;
					break;
				}
				expr.ecExpr.setPresent(true);
			}
			break;
		case GIVEN_EC_PLUS_ONE:
			if (ecOrnc != null)
			{
				expr.comma = new PunctuationComma();
				expr.comma.setPresent(true);
				expr.ecExpr = ecOrnc;
				expr.ecExpr.setPresent(true);
			}
			break;
		case GIVEN_NC:
			expr.comma = new PunctuationComma();
			expr.comma.setPresent(true);
			Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
			Java_Expression scPlusNc = Java_AdditiveExpression.generateAdditive(types, expr.scExpr,
					AdditiveEnum.PLUS, ecOrnc, source);
			expr.ecExpr = scPlusNc;
			expr.ecExpr.setPresent(true);
			break;
		case JUST_ONE:
			expr.ecExpr = null;
			break;
		case TO_END:
			expr.ecExpr = null;
			break;
		}

		// Need to handle ncMightBeTooBig. Can't let ec go past len(left)
		if (ncMightBeTooBig && expr.ecExpr != null)
		{
			Java_Expression len = Java_LengthMethod.generateLength(theExpr, source);
			expr.ecExpr = Java_MathMinMaxFunc.generateMinMax2(MinMaxEnum.MIN, expr.ecExpr, len, source);
			expr.ecExpr.setPresent(true);
		}

		expr.setTransformationSource(source);
		return Java_Generator.wrapExpression(expr);
	}
}
