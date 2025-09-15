// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
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

public class CSharp_SubstringMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword SUBSTRING = new CSharp_Keyword("Substring");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE CSharp_Expression scExpr;
	public @S(60) @OPT @NOSPACE PunctuationComma comma;
	public @S(70) @OPT CSharp_Expression ncExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		int sc = interpreter.getIntValue(scExpr);
		if (ncExpr != null && ncExpr.isPresent())
		{
			int nc = interpreter.getIntValue(ncExpr);
			interpreter.pushStr(leftStr.substring(sc, sc + nc));
		}
		else
		{
			interpreter.pushStr(leftStr.substring(sc));
		}
	}
	
	public static CSharp_SubstringMethod generateExpression(AbstractExpression theExpr,
			AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			AbstractExpression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		CSharp_SubstringMethod expr = new CSharp_SubstringMethod();
		expr.dot = new PunctuationPeriod();
		expr.left = (CSharp_Expression) theExpr;
		expr.leftParen = new PunctuationLeftParen();
		expr.rightParen = new PunctuationRightParen();
		
		switch (whichSC)
		{
		case FIRST_CHAR_IS_ZERO:
			expr.scExpr = (CSharp_Expression) sc;
			break;
		case FIRST_CHAR_IS_ONE:
			CSharp_Number num = new CSharp_Number();
			num.generateNumber("1", source);
			CSharp_Expression one = CSharp_Generator.wrapExpression(num);
			CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			CSharp_Expression scMinusOne = addExp.generateAdditive(types, (CSharp_Expression) sc,
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
				CSharp_Number num = new CSharp_Number();
				CSharp_Expression one = CSharp_Generator.wrapExpression(num.generateNumber("1", source));
				CSharp_AdditiveExpression addExp1 = new CSharp_AdditiveExpression();
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				CSharp_Expression ecPlusOne = addExp1.generateAdditive(types, (CSharp_Expression) ecOrnc,
						AdditiveEnum.PLUS, one, source);
				CSharp_AdditiveExpression addExp2 = new CSharp_AdditiveExpression();
				CSharp_ParenthesizedExpression scParens = new CSharp_ParenthesizedExpression();
				CSharp_Expression scParensExpr = scParens.generateParentheses((CSharp_Expression) sc, null);
				CSharp_Expression ecMinusSc = addExp2.generateAdditive(types, ecPlusOne,
						AdditiveEnum.MINUS, scParensExpr, source);
				CSharp_Expression ncExpr = ecMinusSc;
				expr.ncExpr = ncExpr;
				expr.ncExpr.setPresent(true);
			}
			break;
		case GIVEN_EC_PLUS_ONE:
			if (ecOrnc != null)
			{
				expr.comma = new PunctuationComma();
				expr.comma.setPresent(true);
				CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				CSharp_ParenthesizedExpression scParens = new CSharp_ParenthesizedExpression();
				CSharp_Expression scParensExpr = scParens.generateParentheses((CSharp_Expression) sc, null);
				CSharp_Expression ecMinusSc = addExp.generateAdditive(types, (CSharp_Expression) ecOrnc,
						AdditiveEnum.MINUS, scParensExpr, source);
				CSharp_Expression ncExpr = ecMinusSc;
				expr.ncExpr = ncExpr;
				expr.ncExpr.setPresent(true);
			}
			break;
		case GIVEN_NC:
			expr.comma = new PunctuationComma();
			expr.comma.setPresent(true);
			expr.ncExpr = (CSharp_Expression) ecOrnc;
			expr.ncExpr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			expr.ncExpr = null;
			break;
		}
		
		// Need to handle ncMightBeTooBig. Can't let nc go past len(left) minus scS
		if (ncMightBeTooBig && expr.ncExpr != null)
		{
			CSharp_LengthMethod lenFn = new CSharp_LengthMethod();
			CSharp_Expression lenExp = lenFn.generateLength((CSharp_Expression) theExpr, source);

			CSharp_ParenthesizedExpression parens = new CSharp_ParenthesizedExpression();
			CSharp_Expression parenExp = parens.generateParentheses(expr.scExpr, source);
			
			CSharp_AdditiveExpression subtrExp = new CSharp_AdditiveExpression();
			subtrExp.generateAdditive(null, lenExp, AdditiveEnum.MINUS, parenExp, source);
			
			CSharp_MathMinMaxFunc minFn = new CSharp_MathMinMaxFunc();
			minFn.leftParen = new PunctuationLeftParen();
			minFn.expressions = new SeparatedList<CSharp_Expression, PunctuationComma>();
			minFn.expressions.addPrimaryElement(expr.ncExpr);
			minFn.expressions.addSecondaryElement(new PunctuationComma());
			minFn.expressions.addPrimaryElement(CSharp_Generator.wrapExpression(subtrExp));
			minFn.rightParen = new PunctuationRightParen();
			
			
			CSharp_MathFunction mathFn = CSharp_MathFunction.wrapFunction(minFn, source);
			expr.ncExpr = CSharp_Generator.wrapExpression(mathFn);
			expr.ncExpr.setPresent(true);
		}
		
		expr.setTransformationSource(source);
		return expr;
	}
}
