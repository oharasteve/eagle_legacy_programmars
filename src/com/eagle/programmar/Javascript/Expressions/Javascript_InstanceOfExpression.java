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
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_InstanceOfExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
	public @S(30) Javascript_Type type;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue value = rt.eval(expr);
		AbstractToken which = type.getWhich();
		if (which instanceof Javascript_Variable) interpreter.pushBool(rt.instanceOf(value, Javascript_Variable.evaluate(rt, (Javascript_Variable) which, null, 0)));
		else
		{
			String kind = ((com.eagle.tokens.TerminalToken) which).getValue();
			interpreter.pushBool("Array".equals(kind) ? value.isArray() : "String".equals(kind) ? value.isString() : false);
		}
	}
}
