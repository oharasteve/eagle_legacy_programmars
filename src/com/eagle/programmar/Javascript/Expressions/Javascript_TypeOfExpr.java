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
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_TypeOfExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
	public @S(20) Javascript_Expression what;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		AbstractToken which = what.getWhich();
		if (which instanceof Javascript_VariableExpression && Javascript_Variable.lastQualifier(((Javascript_VariableExpression) which).variable) == null
				&& !rt.isDefined(Javascript_Variable.firstName(((Javascript_VariableExpression) which).variable)))
		{
			interpreter.pushEagleValue(JsValues.str("undefined"));
			return;
		}
		interpreter.pushEagleValue(JsValues.str(JsValues.typeOf(rt.eval(what))));
	}
}
