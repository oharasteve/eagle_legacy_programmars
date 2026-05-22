// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
			sb.append(lit.removeQuotes());
		}
		interpreter.pushStr(sb.toString());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (literals.size() != 1)
		{
			throw new RuntimeException("Cannot handle multiple literals");
		}
		return literals.first().transformExpression(transformer, generator);
	}
}
