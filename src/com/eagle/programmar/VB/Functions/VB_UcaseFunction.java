// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_UcaseFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) VB_KeywordChoice UCASE = new VB_KeywordChoice("Lcase", "Ucase");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) VB_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		switch (UCASE.toString().toLowerCase())
		{
		case "lcase":
			interpreter.pushStr(str.toLowerCase());
			break;
		case "ucase":
			interpreter.pushStr(str.toUpperCase());
			break;
		}
	}
}
