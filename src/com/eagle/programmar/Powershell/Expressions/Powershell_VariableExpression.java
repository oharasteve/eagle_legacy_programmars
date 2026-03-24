// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	// Because Powershell_Variable is not a TerminalToken,
	// it has to be wrapped in a PrimaryOperator
	public @S(10) Powershell_Variable variable;

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
			subscrExpr = transformer.transformExpression(generator, variable.subscript.subscr);
		}
		return generator.newVariableExpression(variable.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, this);
	}
}
