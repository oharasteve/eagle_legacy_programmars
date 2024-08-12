// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_MathFloor extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword MATH = new Javascript_Keyword("Math");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword FLOOR = new Javascript_Keyword("floor");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression expr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double val = interpreter.getDoubleValue(expr);
		interpreter.pushInt((int) val);
	}
}
