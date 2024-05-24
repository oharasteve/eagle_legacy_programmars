// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_ParenthesizedExpression extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT SeparatedList<Javascript_Expression, PunctuationComma> expressions;
	public @S(30) @OPT PunctuationComma comma;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expressions.first());
	}
}
