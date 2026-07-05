// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Fortran_Variable variable;
	public @S(20) PunctuationEquals equals;
	public @S(30) Fortran_Expression expression;
	public @S(40) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		String varName = variable.var.toString();
		
		AbstractFunction abstractFunc = interpreter.findFunction(varName);
		if (abstractFunc != null && abstractFunc instanceof Fortran_Function)
		{
			// Return value from a function
			Fortran_Function func = (Fortran_Function) abstractFunc;
			func._returnMetrics.returned(val.getType());
		}
		
		interpreter.setSymbol(variable, varName, val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Fortran doesn't have a Return statement for Functions.
		// It assigns a value to the function name
		// Returns are allowed in Subroutines.

		AbstractExpression newExpr = transformer.transformExpression(generator, expression);
		String varName = variable.var.getValue();
		
		AbstractToken parent = variable;
		while (parent != null)
		{
			if (parent instanceof Fortran_Function)
			{
				Fortran_Function fn = (Fortran_Function) parent;
				if (fn.id.getValue().equals(varName))
				{
					// It is a function return: function cube(a) begin cube := a*a*a end
					return generator.newReturnStatement(newExpr, this);
				}
				break;
			}
			parent = parent.getParent();
		}

		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
