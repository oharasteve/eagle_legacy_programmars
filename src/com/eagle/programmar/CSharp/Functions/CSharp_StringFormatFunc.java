// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_StringFormatFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) CSharp_Keyword FORMAT = new CSharp_Keyword("format");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSharp_Expression number;
	public @S(40) PunctuationComma comma;
	public @S(50) CSharp_Expression format;
	public @S(60) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num = interpreter.getIntValue(number);
		String fmt = interpreter.getStrValue(format);
		interpreter.pushStr(String.format(fmt, Integer.valueOf(num)));
	}

	public CSharp_Expression generateStringFormat(CSharp_Expression num, CSharp_Expression fmt)
	{
		this.leftParen = new PunctuationLeftParen();
		this.number = num;
		this.comma = new PunctuationComma();
		this.format = fmt;
		this.rightParen = new PunctuationRightParen();
		
		return CSharp_Generator.wrapExpression(this);
	}
}
