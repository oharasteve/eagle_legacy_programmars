// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.tokens.PrecedenceOperator;

public class Python_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Subscript subscr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		Python_Subscript.evaluateSubscript(interpreter, value, subscr);
	}
}
