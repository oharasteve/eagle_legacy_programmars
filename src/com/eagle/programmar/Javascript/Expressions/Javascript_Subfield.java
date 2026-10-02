// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_Subfield extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice dot = new Javascript_PunctuationChoice(".", "?.");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue target = rt.eval(left);
		if ("?.".equals(dot.getValue()) && JsValues.isNullish(target)) { interpreter.pushEagleValue(JsValues.undefined()); return; }
		AbstractToken which = right.getWhich();
		if (which instanceof Javascript_FunctionCall)
		{
			Javascript_FunctionCall call = (Javascript_FunctionCall) which;
			interpreter.pushEagleValue(Javascript_FunctionCall.invoke(rt, call.functionName, call.arguments, target, this));
		}
		else if (which instanceof Javascript_VariableExpression)
		{
			interpreter.pushEagleValue(Javascript_Variable.evaluate(rt, ((Javascript_VariableExpression) which).variable, target, 0));
		}
		else if (which instanceof com.eagle.programmar.Javascript.Functions.Javascript_Length)
		{
			com.eagle.programmar.Javascript.Functions.Javascript_Length len = (com.eagle.programmar.Javascript.Functions.Javascript_Length) which;
			interpreter.pushEagleValue(rt.getProperty(Javascript_Variable.evaluate(rt, len.variableName, target, 0), "length", false));
		}
		else
		{
			throw new RuntimeException("Cannot read a " + which.getClass().getSimpleName() + " as a member");
		}
	}
}
