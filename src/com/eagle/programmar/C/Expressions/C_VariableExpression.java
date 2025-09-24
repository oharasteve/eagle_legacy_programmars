// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscrExpr = null;
		if (variable.subscript != null && variable.subscript.size() > 0)
		{
			subscrExpr = transformer.transformExpression(generator, variable.subscript.first().expr);
		}
		AbstractToken which = variable.firstId.getWhich();
		String varName;
		if (which instanceof C_Identifier_Reference)
		{
			C_Identifier_Reference idRef = (C_Identifier_Reference) which;
			varName = idRef.getValue();
		}
		else
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		return generator.newVariableExpression(varName,
				SubscriptEnum.FIRST_IS_ONE, subscrExpr, this);
	}
}
