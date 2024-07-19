// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Javascript_Subfield extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (right.getWhich() instanceof Javascript_VariableExpression)
		{
			Javascript_VariableExpression var = (Javascript_VariableExpression) right.getWhich();
			AbstractToken which = var.variable.firstId.getWhich();
			if (which instanceof Javascript_Identifier_Reference)
			{
				Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
				if (id.getValue().equals("length"))
				{
					Javascript_BuiltinFunction.length(interpreter, left);
					return;
				}
			}
		}
		
		throw new RuntimeException("Unable to handle subfield: " + right);
	}
}
