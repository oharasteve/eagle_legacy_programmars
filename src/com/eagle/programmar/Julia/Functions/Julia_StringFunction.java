// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_StringFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Julia_Keyword STRING = new Julia_Keyword("string");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Julia_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < argList.getPrimaryCount(); i++)
		{
			Julia_Expression expr = argList.getPrimaryElement(i);
			String val = interpreter.getStrValue(expr);
			buff.append(val);
		}
		interpreter.pushStr(buff.toString());
	}
}
