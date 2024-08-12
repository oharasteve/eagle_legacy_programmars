// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Library;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_FloorFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Powershell_Library library;
	public @S(20) Powershell_Keyword FLOOR = new Powershell_Keyword("Floor");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) Powershell_Expression expr;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// if (library.name.first().getValue().equals("Math"))
		int val = interpreter.getIntValue(expr);
		interpreter.pushInt(val);
	}
}
