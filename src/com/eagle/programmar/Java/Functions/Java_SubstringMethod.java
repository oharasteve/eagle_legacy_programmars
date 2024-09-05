// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringEnum;

public class Java_SubstringMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Java_Keyword SUBSTRING = new Java_Keyword("substring");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Java_Expression scExpr;
	public @S(60) @OPT PunctuationComma comma;
	public @S(70) @OPT Java_Expression ecExpr;
	public @S(80) PunctuationRightParen rightParen;
	
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
	
	public static Java_SubstringMethod generateExpression(AbstractExpression theExpr, AbstractExpression sc,
			SubstringEnum which, AbstractExpression ecOrnc, AbstractToken source)
	{
		Java_SubstringMethod expr = new Java_SubstringMethod();
		expr.left = (Java_Expression) theExpr;
		expr.scExpr = (Java_Expression) sc;
		
		switch (which)
		{
		case GIVEN_EC:
			expr.ecExpr = (Java_Expression) ecOrnc;
			expr.ecExpr.setPresent(true);
			break;
		case GIVEN_NC:
			Java_AdditiveExpression scPlusNc = Java_AdditiveExpression.generateExpression(sc, AdditiveEnum.PLUS, ecOrnc, source);
			expr.ecExpr = Java_Generator.wrapExpression(scPlusNc);
			expr.ecExpr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			expr.ecExpr = null;
			break;
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
