// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 4, 2024

package com.eagle.programmar.Eaglish.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_LengthFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_Keyword LENGTH = new Eaglish_Keyword("LENGTH");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Eaglish_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		interpreter.pushInt(val.length());
	}
}
