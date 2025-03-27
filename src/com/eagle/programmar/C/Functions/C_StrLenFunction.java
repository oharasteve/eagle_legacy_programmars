// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_StrLenFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Keyword STRLEN = new C_Keyword("strlen");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String string = interpreter.getStrValue(expr);
		interpreter.pushInt(string.length());
	}
}
