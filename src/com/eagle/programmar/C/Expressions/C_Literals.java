// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class C_Literals extends PrimaryOperator implements EagleRunnable
{
	public @S(10) TokenList<C_Literal> literals;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer sb = new StringBuffer();
		for (C_Literal lit : literals._elements)
		{
			sb.append(lit.toString());
		}
		interpreter.pushStr(sb.toString());
	}
}
