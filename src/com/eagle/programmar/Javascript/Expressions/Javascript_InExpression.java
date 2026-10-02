// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_InExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_Keyword inOperator = new Javascript_Keyword("in");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue key = rt.eval(left);
		EagleValue target = rt.eval(right);
		if (target instanceof JsValues.JsObject) interpreter.pushBool(((JsValues.JsObject) target).has(JsValues.toText(key)));
		else if (target.isArray()) { int i = (int) JsValues.toNumber(key); interpreter.pushBool(i >= 0 && i < JsValues.items(target).size()); }
		else interpreter.pushBool(false);
	}
}
