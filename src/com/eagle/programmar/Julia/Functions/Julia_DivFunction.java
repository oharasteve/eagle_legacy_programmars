// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_DivFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Julia_Keyword DIV = new Julia_Keyword("div");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Julia_Expression numerExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Julia_Expression denomExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numer = interpreter.getIntValue(numerExpr);
		int denom = interpreter.getIntValue(denomExpr);
		interpreter.pushInt(numer / denom);
	}
}
