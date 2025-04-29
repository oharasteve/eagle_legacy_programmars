// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Subscript;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Variable_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscript = null;
		if (variable.extensions != null && variable.extensions.size() > 0)
		{
			AbstractToken first = variable.extensions.first().getWhich();
			if (first instanceof Delphi_Subscript)
			{
				Delphi_Subscript sub = (Delphi_Subscript) first;
				subscript = transformer.transformExpression(generator, sub.exprs.first());
			}
		}
		return generator.newVariableExpression(variable.var.getValue(), subscript, this);
	}
}
