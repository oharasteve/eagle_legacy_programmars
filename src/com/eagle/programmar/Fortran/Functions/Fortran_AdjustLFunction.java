// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_AdjustLFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Fortran_Keyword ADJUSTL = new Fortran_Keyword("ADJUSTL");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		String trimmedStr = str.stripLeading();
		int lengthDifference = str.length() - trimmedStr.length();
		String newStr = trimmedStr + str.substring(0, lengthDifference);
		interpreter.pushStr(newStr);	// Left justifies a string, but keeps length same
	}
}