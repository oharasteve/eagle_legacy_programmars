// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class TCL_BangExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) TCL_Punctuation bang = new TCL_Punctuation('!');
	public @S(20) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean val = interpreter.getBoolValue(expr);
		interpreter.pushBool(! val);
	}
}
