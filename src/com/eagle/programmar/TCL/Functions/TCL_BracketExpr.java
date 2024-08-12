// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class TCL_BracketExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword EXPR = new TCL_Keyword("expr");
	public @S(30) TCL_Expression expr;
	public @S(40) PunctuationRightBracket rightBracket;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
