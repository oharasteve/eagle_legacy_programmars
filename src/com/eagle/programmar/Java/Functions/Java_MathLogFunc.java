// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MathLogFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) Java_KeywordChoice LOG = new Java_KeywordChoice("log", "log10");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Java_Expression number;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		switch (LOG.getValue())
		{
		case "log":
			interpreter.pushDouble(Math.log(num));
			break;
		case "log10":
			interpreter.pushDouble(Math.log10(num));
			break;
		default:
			throw new RuntimeException("Unable to handle " + LOG);
		}
	}
}
