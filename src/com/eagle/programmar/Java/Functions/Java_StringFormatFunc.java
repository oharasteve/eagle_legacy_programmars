// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2025

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_StringFormatFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) Java_Keyword FORMAT = new Java_Keyword("format");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression format;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) Java_Expression number;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num = interpreter.getIntValue(number);
		String fmt = interpreter.getStrValue(format);
		interpreter.pushStr(String.format(fmt, Integer.valueOf(num)));
	}

	public Java_Expression generateStringFormat(Java_Expression num, Java_Expression fmt,
			AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.number = num;
		this.comma = new PunctuationComma();
		this.format = fmt;
		this.rightParen = new PunctuationRightParen();
		return Java_StringFunction.wrapStringFunction(this, source);
	}
}
