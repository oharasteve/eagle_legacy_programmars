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

public class Javascript_DeleteExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword DELETE = new Javascript_Keyword("delete");
	public @S(20) Javascript_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		AbstractToken which = expr.getWhich();
		if (which instanceof Javascript_VariableExpression)
		{
			Javascript_Variable v = ((Javascript_VariableExpression) which).variable;
			AbstractToken last = Javascript_Variable.lastQualifier(v);
			EagleValue receiver = last == null ? null : Javascript_Variable.evaluate(rt, v, null, 1);
			if (receiver instanceof JsValues.JsObject)
			{
				String key = last instanceof Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField
						? ((Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField) last).id.getValue()
						: JsValues.toText(rt.eval(((com.eagle.programmar.Javascript.Javascript_Subscript) last).expr));
				((JsValues.JsObject) receiver).props.remove(key);
			}
		}
		interpreter.pushBool(true);
	}
}
