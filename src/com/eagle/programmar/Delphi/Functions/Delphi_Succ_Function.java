// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2024

package com.eagle.programmar.Delphi.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Succ_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_Keyword PRED = new Delphi_Keyword("Pred");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num = interpreter.getIntValue(expr);
		interpreter.pushInt(num + 1);
	}
}
