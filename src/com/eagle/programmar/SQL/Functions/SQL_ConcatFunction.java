// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2025

package com.eagle.programmar.SQL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_ConcatFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) SQL_Keyword CONCAT = new SQL_Keyword("CONCAT");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<SQL_Expression, PunctuationComma> exprs;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			String piece = interpreter.getStrValue(exprs.getPrimaryElement(i));
			result.append(piece);
		}
		interpreter.pushStr(result.toString());
	}
}
