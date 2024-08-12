// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_LengthFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_Keyword LENGTH = new Ada_Keyword("Length");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Ada_Expression arg;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String unbound = interpreter.getStrValue(arg);
		interpreter.pushInt(unbound.length());
	}
}
