// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_StartsWithFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Julia_Keyword STARTSWITH = new Julia_Keyword("startswith");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Julia_Expression strExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Julia_Expression pattExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(strExpr);
		String patt = interpreter.getStrValue(pattExpr);
		interpreter.pushBool(str.startsWith(patt));
	}
}
