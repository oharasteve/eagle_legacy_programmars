// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MathPowFunction extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Keyword MATH = new Java_Keyword("Math");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Java_Keyword POW = new Java_Keyword("pow");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Java_Expression number;
	public @S(60) PunctuationComma comma;
	public @S(70) Java_Expression power;
	public @S(80) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		double pow = interpreter.getDoubleValue(power);
		interpreter.pushDouble(Math.pow(num, pow));
	}
	
	public static Java_MathPowFunction generateExpression(AbstractExpression number, AbstractExpression power, AbstractToken source)
	{
		Java_MathPowFunction pow = new Java_MathPowFunction();
		pow.number = (Java_Expression) number;
		pow.power = (Java_Expression) power;
		pow.setTransformationSource(source);
		return pow;
	}
}
