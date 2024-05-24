// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMD_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) CMD_Expression expression;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}
}
