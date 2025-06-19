// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class VB_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		VB_Expression subscript = null;
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			subscript = variable.subscript.exprs.first();
		}
		return generator.newVariableExpression(variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscript, this);
	}
}
