// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_ChrFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Basic_Keyword CHR = new Basic_Keyword("CHR$");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Basic_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		char x = (char) interpreter.getIntValue(expr);
		interpreter.pushStr(Character.toString(x));
	}
}
