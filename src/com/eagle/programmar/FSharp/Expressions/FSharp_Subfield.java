// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class FSharp_Subfield extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// See if it is builtin function
		if (right.getWhich() instanceof FSharp_FunctionCall)
		{
			FSharp_FunctionCall fn = (FSharp_FunctionCall) right.getWhich();
			String name = fn.functionName.id.getValue();
			if (name.equals("StartsWith") && fn.argList.getPrimaryCount() == 1)
			{
				FSharp_Expression pattExpr = fn.argList.getPrimaryElement(0);
				String str = interpreter.getStrValue(left);
				String patt = interpreter.getStrValue(pattExpr);
				interpreter.pushBool(str.startsWith(patt));
				return;
			}
		}
		if (right.getWhich() instanceof FSharp_VariableExpression)
		{
			FSharp_VariableExpression var = (FSharp_VariableExpression) right.getWhich();
			String name = var.variable.id.getValue();
			if (name.equals("Length"))
			{
				String str = interpreter.getStrValue(left);
				interpreter.pushInt(str.length());
				return;
			}
		}
		
		throw new RuntimeException("Unable to process subfield " + left + " . " + right);
	}
}
