// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_IntFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Keyword INT = new AWK_Keyword("int");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double intArg = interpreter.getDoubleValue(expr);
		interpreter.pushInt((int) intArg);
	}
}
