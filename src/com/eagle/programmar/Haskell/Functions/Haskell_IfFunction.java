// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_IfFunction extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Haskell_Keyword IF = new Haskell_Keyword("if");
	public @S(20) Haskell_Expression condition;
	public @S(30) @OPT Haskell_EndOfLine eoln1;
	public @S(40) Haskell_Keyword THEN = new Haskell_Keyword("then");
	public @S(50) Haskell_Expression thenExpr;
	public @S(60) @OPT Haskell_EndOfLine eoln2;
	public @S(70) Haskell_Keyword ELSE = new Haskell_Keyword("else");
	public @S(80) Haskell_Expression elseExpr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(condition);
		if (value)
		{
			interpreter.pushEagleValue(interpreter.getEagleValue(thenExpr));
		}
		else
		{
			interpreter.pushEagleValue(interpreter.getEagleValue(elseExpr));
		}
	}
}
