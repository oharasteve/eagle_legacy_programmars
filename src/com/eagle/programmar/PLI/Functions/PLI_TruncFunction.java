// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_TruncFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PLI_Keyword TRUNC = new PLI_Keyword("TRUNC");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PLI_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double x = interpreter.getDoubleValue(expr);
		interpreter.pushInt((int) x);
	}
}
