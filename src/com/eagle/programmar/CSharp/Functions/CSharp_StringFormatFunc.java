// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_StringFormatFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) CSharp_Keyword FORMAT = new CSharp_Keyword("Format");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression format;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) CSharp_Expression number;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num = interpreter.getIntValue(number);
		String fmt = interpreter.getStrValue(format);
		interpreter.pushStr(String.format(fmt, Integer.valueOf(num)));
	}

	public static CSharp_Expression generateStringFormat(CSharp_Expression num, CSharp_Expression fmt,
			AbstractToken source)
	{
		CSharp_StringFormatFunc strFmt = new CSharp_StringFormatFunc();
		strFmt.leftParen = new PunctuationLeftParen();
		strFmt.format = fmt;
		strFmt.comma = new PunctuationComma();
		strFmt.number = num;
		strFmt.rightParen = new PunctuationRightParen();

		return CSharp_StringFunction.wrapStringFunction(strFmt, source);
	}
}
