// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Ada_LogicalNotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_Punctuation logicalNotOperator = new Ada_Punctuation('~');
	public @S(20) Ada_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(!value);
	}
}