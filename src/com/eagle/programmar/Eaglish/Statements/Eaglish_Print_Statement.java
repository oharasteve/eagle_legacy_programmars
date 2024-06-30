// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Eaglish_Print_Statement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Eaglish_Keyword PRINT = new Eaglish_Keyword("PRINT");
	public @S(20) Eaglish_Expression expr;
	public @S(30) Eaglish_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expr);
		System.out.println(result.toString());
	}
}
