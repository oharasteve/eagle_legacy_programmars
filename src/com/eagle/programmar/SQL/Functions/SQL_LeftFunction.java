// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.SQL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_LeftFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) SQL_Keyword LEFT = new SQL_Keyword("LEFT");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SQL_Expression expr;
	public @S(40) PunctuationComma comma;
	public @S(50) SQL_Expression ncExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String strArg = interpreter.getStrValue(expr);
		int nc = interpreter.getIntValue(ncExpr);
		if (nc > strArg.length()) nc = strArg.length();
		interpreter.pushStr(strArg.substring(0, nc));
	}
}
