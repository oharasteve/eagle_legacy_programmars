// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_MathPowFunc extends TokenSequence
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Keyword POW = new Java_Keyword("pow");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression number;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) Java_Expression power;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		double pow = interpreter.getDoubleValue(power);
		interpreter.pushDouble(Math.pow(num, pow));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression numExpr = transformer.transformExpression(generator, number);
		AbstractExpression powExpr = transformer.transformExpression(generator, power);
		return generator.newExponentExpression(numExpr, powExpr, POW);
	}

	public static Java_Expression generateExpression(AbstractExpression number, AbstractExpression power,
			AbstractToken source)
	{
		Java_MathPowFunc pow = new Java_MathPowFunc();
		pow.leftParen = new PunctuationLeftParen();
		pow.number = (Java_Expression) number;
		pow.comma = new PunctuationComma();
		pow.power = (Java_Expression) power;
		pow.rightParen = new PunctuationRightParen();
		pow.setTransformationSource(source);
		return Java_MathFunction.wrapMathFunction(pow, source);
	}
}
