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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MathAbsFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) Java_Keyword ABS = new Java_Keyword("abs");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Java_Expression number;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		interpreter.pushDouble(Math.abs(num));
	}

	public static Java_Expression generateAbsFunc(AbstractExpression number, AbstractToken source)
	{
		Java_MathAbsFunc abs = new Java_MathAbsFunc();
		abs.leftParen = new PunctuationLeftParen();
		abs.number = (Java_Expression) number;
		abs.rightParen = new PunctuationRightParen();
		abs.setTransformationSource(source);
		return Java_MathFunction.wrapMathFunction(abs, source);
	}
}
