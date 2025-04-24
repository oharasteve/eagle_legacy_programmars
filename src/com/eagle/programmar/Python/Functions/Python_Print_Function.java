// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Print_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE SeparatedList<Python_Expression,PunctuationComma> exprs;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numExpr = exprs.getPrimaryCount();
		for (int i = 0; i < numExpr; i++)
		{
			String line = interpreter.getStrValue(exprs.getPrimaryElement(i));
			System.out.println(line);
		}
	}
}
