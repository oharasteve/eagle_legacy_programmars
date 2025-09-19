// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Subscript;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression newSub = null;
		if (variable.qualifiers != null && variable.qualifiers.size() == 1)
		{
			Javascript_VariableQualifier qual = variable.qualifiers.first();
			if (qual.getWhich() instanceof Javascript_Subscript)
			{
				Javascript_Subscript sub = (Javascript_Subscript) qual.getWhich();
				newSub = transformer.transformExpression(generator, sub.expr);
			}
		}
		
		AbstractToken which = variable.firstId.getWhich();
		if (! (which instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Cannot handle variable: " + which);
		}
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
			return generator.newVariableExpression(id.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, newSub, this);
	}
}
