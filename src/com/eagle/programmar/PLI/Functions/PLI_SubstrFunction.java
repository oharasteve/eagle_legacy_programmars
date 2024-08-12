// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_SubstrFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PLI_Keyword SUBSTR = new PLI_Keyword("SUBSTR");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PLI_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) PLI_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) PLI_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		int nc = interpreter.getIntValue(ncExpr);
		interpreter.pushStr(str.substring(sc, sc + nc));
	}
}
