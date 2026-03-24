// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.AWK_Variable.AWK_VarSubscript;
import com.eagle.programmar.AWK.Terminals.AWK_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_Variable variable;

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
		if (variable.subscripts != null && variable.subscripts.size() == 1)
		{
			AWK_VarSubscript varSub = variable.subscripts.first();
			AbstractToken which = varSub.expr.getWhich();
			if (which instanceof AWK_Number)
			{
				AWK_Number number = (AWK_Number) which;
				subscrExpr = generator.newNumberExpression(number.getValue(), varSub);
			}
			else if (which instanceof AWK_VariableExpression)
			{
				AWK_VariableExpression varExpr = (AWK_VariableExpression) which;
				subscrExpr = generator.newVariableExpression(varExpr.variable.id.getValue(),
						SubscriptEnum.IT_IS_A_HASHMAP, null, varSub);
			}
			else
			{
				throw new RuntimeException("Unexpected subscript: " + which);
			}
		}

		String name = variable.id.getValue();
		if (name.equalsIgnoreCase("true"))
		{
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, variable);
		}
		if (name.equalsIgnoreCase("false"))
		{
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, variable);
		}
		return generator.newVariableExpression(name,
				SubscriptEnum.IT_IS_A_HASHMAP, subscrExpr, this);
	}
}
