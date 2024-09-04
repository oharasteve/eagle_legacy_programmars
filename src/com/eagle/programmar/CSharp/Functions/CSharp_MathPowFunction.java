// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MathPowFunction extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Keyword MATH = new CSharp_Keyword("Math");
	public @S(20) PunctuationPeriod dot;
	public @S(30) CSharp_Keyword POW = new CSharp_Keyword("Pow");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSharp_Expression number;
	public @S(60) PunctuationComma comma;
	public @S(70) CSharp_Expression power;
	public @S(80) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		double pow = interpreter.getDoubleValue(power);
		interpreter.pushDouble(Math.pow(num, pow));
	}
	
	public static CSharp_MathPowFunction generateExpression(AbstractExpression number, AbstractExpression power, AbstractToken source)
	{
		CSharp_MathPowFunction pow = new CSharp_MathPowFunction();
		pow.number = (CSharp_Expression) number;
		pow.power = (CSharp_Expression) power;
		pow.setTransformationSource(source);
		return pow;
	}
}
