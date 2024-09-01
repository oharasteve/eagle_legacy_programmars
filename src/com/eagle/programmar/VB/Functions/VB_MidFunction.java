// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_MidFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) VB_Keyword MID = new VB_Keyword("MID");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) VB_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) VB_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) VB_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		int nc = interpreter.getIntValue(ncExpr);
		int len = str.length();
		if (sc + nc > len) nc = len - sc;	// Don't go past the end of the string
		interpreter.pushStr(str.substring(sc, sc + nc));
	}
}
