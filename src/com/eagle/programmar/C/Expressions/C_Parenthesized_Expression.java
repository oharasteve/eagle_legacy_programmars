// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.C.C_ParenthesizedExpressions;
import com.eagle.tokens.PrimaryOperator;

public class C_Parenthesized_Expression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_ParenthesizedExpressions exprs;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(exprs.expression.first());
	}
}
