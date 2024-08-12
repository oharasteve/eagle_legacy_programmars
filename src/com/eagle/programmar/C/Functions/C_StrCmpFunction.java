// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_StrCmpFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Keyword STRCMP = new C_Keyword("strcmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression str1;
	public @S(40) PunctuationComma comma;
	public @S(50) C_Expression str2;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(str1);
		String right = interpreter.getStrValue(str2);
		interpreter.pushInt(left.compareTo(right));
	}
}
