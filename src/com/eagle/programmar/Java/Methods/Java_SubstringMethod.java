// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Functions.Java_MathFunction;
import com.eagle.programmar.Java.Functions.Java_MathMinMaxFunc;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

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

	public static Java_SubstringMethod generateExpression(AbstractExpression theExpr,
			AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			AbstractExpression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		Java_SubstringMethod expr = new Java_SubstringMethod();
		expr.dot = new PunctuationPeriod();
		expr.left = (Java_Expression) theExpr;
		expr.leftParen = new PunctuationLeftParen();
		expr.rightParen = new PunctuationRightParen();

		switch (whichSC)
		{
		case FIRST_CHAR_IS_ZERO:
			expr.scExpr = (Java_Expression) sc;
			break;
		case FIRST_CHAR_IS_ONE:
			Java_Number num = new Java_Number();
			Java_Expression one = Java_Generator.wrapExpression(num.generateNumber("1", source));
			Java_AdditiveExpression addExp = new Java_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Java_Expression scMinusOne = addExp.generateAdditive(types, (Java_Expression) sc,
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
					Java_Number num = new Java_Number();
					Java_Expression one = Java_Generator.wrapExpression(num.generateNumber("1", source));
					Java_AdditiveExpression addExp = new Java_AdditiveExpression();
					Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
					Java_Expression ecPlusOne = addExp.generateAdditive(types, (Java_Expression) ecOrnc,
							AdditiveEnum.PLUS, one, source);
					expr.ecExpr = ecPlusOne;
					break;
				case FIRST_CHAR_IS_ONE:
					expr.ecExpr = (Java_Expression) ecOrnc;
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
				expr.ecExpr = (Java_Expression) ecOrnc;
				expr.ecExpr.setPresent(true);
			}
			break;
		case GIVEN_NC:
			expr.comma = new PunctuationComma();
			expr.comma.setPresent(true);
			Java_AdditiveExpression addExp = new Java_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Java_Expression scPlusNc = addExp.generateAdditive(types, expr.scExpr,
					AdditiveEnum.PLUS, (Java_Expression) ecOrnc, source);
			expr.ecExpr = scPlusNc;
			expr.ecExpr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			expr.ecExpr = null;
			break;
		}

		// Need to handle ncMightBeTooBig. Can't let ec go past len(left)
		if (ncMightBeTooBig && expr.ecExpr != null)
		{
			Java_MathMinMaxFunc minFn = new Java_MathMinMaxFunc();
			minFn.leftParen = new PunctuationLeftParen();
			minFn.expressions = new SeparatedList<Java_Expression, PunctuationComma>();
			minFn.expressions.addPrimaryElement(expr.ecExpr);
			minFn.expressions.addSecondaryElement(new PunctuationComma());
			minFn.rightParen = new PunctuationRightParen();

			Java_LengthMethod lenFn = new Java_LengthMethod();
			minFn.expressions.addPrimaryElement(lenFn.generateLength((Java_Expression) theExpr, source));

			Java_MathFunction mathFn = Java_MathFunction.wrapMathFunction(minFn, source);
			expr.ecExpr = Java_Generator.wrapExpression(mathFn);
			expr.ecExpr.setPresent(true);
		}

		expr.setTransformationSource(source);
		return expr;
	}
}
