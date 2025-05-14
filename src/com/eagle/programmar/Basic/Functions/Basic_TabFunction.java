// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 13, 2025

package com.eagle.programmar.Basic.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_TabFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Basic_Keyword TAB = new Basic_Keyword("TAB");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Basic_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int tab = interpreter.getIntValue(expr);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tab; i++)
		{
			sb.append(' ');
		}
		interpreter.pushStr(sb.toString());
	}
}
