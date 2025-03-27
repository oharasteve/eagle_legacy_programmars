// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_StrNCmpFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Keyword STRNCMP = new C_Keyword("strncmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression str1;
	public @S(40) PunctuationComma comma1;
	public @S(50) C_Expression str2;
	public @S(60) PunctuationComma comma2;
	public @S(70) C_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(str1);
		String right = interpreter.getStrValue(str2);
		int nc = interpreter.getIntValue(ncExpr);
		if (left.length() > nc) left = left.substring(0, nc);
		if (right.length() > nc) right = right.substring(0, nc);
		interpreter.pushInt(left.compareTo(right));
	}
}
