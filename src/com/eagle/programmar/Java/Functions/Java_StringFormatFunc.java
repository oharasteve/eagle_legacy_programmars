// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2024

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
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Java_Expression number;
	public @S(40) PunctuationComma comma;
	public @S(50) Java_Expression digits;
	public @S(60) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num = interpreter.getIntValue(number);
		int dig = interpreter.getIntValue(digits);
		String fmt = "%" + dig + "d";
		interpreter.pushStr(String.format(fmt, Integer.valueOf(num)));
	}

	public Java_StringFunction generateStringFormat(Java_Expression num,
			Java_Expression digs, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.number = num;
		this.comma = new PunctuationComma();
		this.digits = digs;
		this.rightParen = new PunctuationRightParen();
		
		return Java_StringFunction.wrapStringFunction(this, source);
	}
}
