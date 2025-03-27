// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MathPowFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) @NOSPACE CSharp_Keyword POW = new CSharp_Keyword("Pow");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression number;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) CSharp_Expression power;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		double pow = interpreter.getDoubleValue(power);
		interpreter.pushDouble(Math.pow(num, pow));
	}
	
	public static CSharp_MathFunction generateExpression(AbstractExpression number, AbstractExpression power, AbstractToken source)
	{
		CSharp_MathPowFunc pow = new CSharp_MathPowFunc();
		pow.leftParen = new PunctuationLeftParen();
		pow.number = (CSharp_Expression) number;
		pow.comma = new PunctuationComma();
		pow.power = (CSharp_Expression) power;
		pow.rightParen = new PunctuationRightParen();
		pow.setTransformationSource(source);
		return CSharp_MathFunction.wrapFunction(pow, source);
	}
}
