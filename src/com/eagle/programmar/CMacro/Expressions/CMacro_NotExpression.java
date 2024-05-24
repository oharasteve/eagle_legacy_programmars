// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CMacro_NotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CMacro_Punctuation notOperator = new CMacro_Punctuation('!');
	public @S(20) CMacro_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean val = interpreter.getBoolValue(expr);
		interpreter.pushBool(!val);
	}
}
