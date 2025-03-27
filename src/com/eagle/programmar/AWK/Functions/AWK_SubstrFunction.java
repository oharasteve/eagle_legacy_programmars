// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_SubstrFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Keyword SUBSTR = new AWK_Keyword("substr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) AWK_Expression scExpr;
	public @S(60) @OPT PunctuationComma comma2;
	public @S(70) @OPT AWK_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String strArg = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		if (sc > strArg.length()) throw new RuntimeException("Error on substr for " + strArg);
		if (ncExpr != null && ncExpr.isPresent())
		{
			int nc = interpreter.getIntValue(ncExpr);
			if (sc + nc > strArg.length()) nc = strArg.length() - sc;
			interpreter.pushStr(strArg.substring(sc, sc + nc)); // AWK substr() starts with 1, not 0
		}
		else
		{
			interpreter.pushStr(strArg.substring(sc));
		}
	}
}
