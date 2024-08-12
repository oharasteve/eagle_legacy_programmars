// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Copy_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_Keyword COPY = new Delphi_Keyword("Copy");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) Delphi_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) Delphi_Expression ecExpr;
	public @S(80) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		int ec = interpreter.getIntValue(ecExpr) + sc;
		int nc = str.length();
		if (ec > nc) ec = nc; 
		interpreter.pushStr(str.substring(sc, ec));
	}
}
