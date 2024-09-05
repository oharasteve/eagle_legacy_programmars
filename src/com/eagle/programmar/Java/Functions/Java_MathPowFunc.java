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

public class Java_MathPowFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) Java_Keyword POW = new Java_Keyword("pow");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Java_Expression number;
	public @S(40) PunctuationComma comma;
	public @S(50) Java_Expression power;
	public @S(60) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		double pow = interpreter.getDoubleValue(power);
		interpreter.pushDouble(Math.pow(num, pow));
	}
	
	public static Java_MathFunction generateExpression(AbstractExpression number, AbstractExpression power, AbstractToken source)
	{
		Java_MathPowFunc pow = new Java_MathPowFunc();
		pow.number = (Java_Expression) number;
		pow.power = (Java_Expression) power;
		pow.setTransformationSource(source);
		return Java_MathFunction.wrapFunction(pow, source);
	}
}
