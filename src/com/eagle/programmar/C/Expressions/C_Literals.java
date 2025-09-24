// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_Literals extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (literals.size() != 1)
		{
			throw new RuntimeException("Cannot handle multiple literals");
		}
		return generator.newLiteralExpression(literals.first().getValue(), this);
	}
}
