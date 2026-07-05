// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.SubstringECEnum;
import com.eagle.generate.SubstringSCEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Algol68_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression subscript = null;
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			subscript = transformer.transformExpression(generator, variable.subscript.expr);
			if (variable.subscript.colonSub != null && variable.subscript.colonSub.isPresent())
			{
				AbstractExpression ecExpr = transformer.transformExpression(
						generator, variable.subscript.colonSub.expr2);
				AbstractExpression varExpr = generator.newVariableExpression(
						variable.vars.first().getValue(), SubscriptEnum.FIRST_IS_ONE,
						null, variable);
				return generator.newSubstringFunction(varExpr,
						subscript, SubstringSCEnum.FIRST_CHAR_IS_ONE,
						SubstringECEnum.GIVEN_EC, ecExpr, false, this);
			}
		}
		// Actually, this depends on how the array is defined: Array[0..9] of String
		return generator.newVariableExpression(variable.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ONE, subscript, this);
	}
}
