// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_EqualsMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword EQUALS = new Java_Keyword("equals");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Java_Expression expr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String other = interpreter.getStrValue(expr);
		interpreter.pushBool(leftStr.equals(other));
	}
	
	public static Java_EqualsMethod newEqualsMethod(Java_Expression leftExpr, Java_Expression rightExpr)
	{
		Java_EqualsMethod equals = new Java_EqualsMethod();
		equals.left = leftExpr;
		equals.dot = new PunctuationPeriod();
		equals.leftParen = new PunctuationLeftParen();
		equals.expr = rightExpr;
		equals.rightParen = new PunctuationRightParen();
		return equals;
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, expr);
		Oper2Types types = new Oper2Types(EagleString.STRING, EagleString.STRING);
		return generator.newRelationalExpression(types, leftExpr,	
				RelationalEnum.EQUALS, rightExpr, this);
	}
}
