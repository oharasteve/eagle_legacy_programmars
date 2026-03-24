// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Rexx_Variable;
import com.eagle.programmar.Rexx.Terminals.Rexx_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rexx_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rexx_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression subscrExpr = null;
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			AbstractToken which = variable.subscript.subscr.getWhich();
			if (which instanceof Rexx_Number)
			{
				Rexx_Number number = (Rexx_Number) which;
				subscrExpr = generator.newNumberExpression(number.getValue(),
						variable.subscript.subscr);
			}
			else if (which instanceof Rexx_Variable)
			{
				Rexx_Variable var = (Rexx_Variable) which;
				subscrExpr = generator.newVariableExpression(var.var.getValue(),
						SubscriptEnum.IT_IS_A_HASHMAP, null, variable.subscript.subscr);
			}
			else
			{
				throw new RuntimeException("Unexpected subscript: " + which);
			}
		}
		return generator.newVariableExpression(variable.var.getValue(),
				SubscriptEnum.IT_IS_A_HASHMAP, subscrExpr, this);
	}
}
