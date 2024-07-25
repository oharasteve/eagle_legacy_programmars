// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Python_Subfield extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (right.getWhich() instanceof Python_Function_Call)
		{
			Python_Function_Call fnCall = (Python_Function_Call) right.getWhich();
			String name = "unknown";
			if (fnCall.fnName.var.getWhich() instanceof Python_Identifier_Reference)
			{
				Python_Identifier_Reference id = (Python_Identifier_Reference) fnCall.fnName.var.getWhich();
				name = id.getValue();
			}

			if (name.equals("startswith"))
			{
				String str = interpreter.getStrValue(left);
				String patt = interpreter.getStrValue(fnCall.argList.first());
				interpreter.pushBool(str.startsWith(patt));
				return;
			}
		}
		
		throw new RuntimeException("Unable to handle " + left + " . " + right);
	}
}
