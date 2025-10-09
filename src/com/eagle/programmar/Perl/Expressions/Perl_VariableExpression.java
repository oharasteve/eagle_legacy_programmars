// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (! (variable.getWhich() instanceof Perl_UserVariable))
		{
			throw new RuntimeException("Can only handle simple variables");
		}
		Perl_UserVariable userVar = (Perl_UserVariable) variable.getWhich();

		AbstractExpression subscr = null;
		if (userVar.subscript != null && userVar.subscript.size() > 0)
		{
			subscr = transformer.transformExpression(generator, userVar.subscript.first().expr);
		}
		
		String newName = Perl_Variable.repairName(userVar.id.getValue());
		return generator.newVariableExpression(newName,
				SubscriptEnum.FIRST_IS_ZERO, subscr, this);
	}
}
