// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) TCL_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		TCL_Expression subscript = null;
		return generator.newVariableExpression(variable.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscript, variable);
	}
}
