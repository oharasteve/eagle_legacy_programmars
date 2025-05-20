// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

package com.eagle.programmar.Basic.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_RndFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Basic_Keyword RND = new Basic_Keyword("RND");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Basic_Keyword X = new Basic_Keyword("X");
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushDouble(Math.random());
	}
}
