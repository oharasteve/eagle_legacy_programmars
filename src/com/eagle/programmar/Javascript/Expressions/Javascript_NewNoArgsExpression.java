// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_NewNoArgsExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
	public @S(20) Javascript_Type jtype;

	public static EagleValue construct(JsRuntime rt, Javascript_Type jtype, Javascript_ParenthesizedExpression arguments, AbstractToken site)
	{
		java.util.List<EagleValue> args = rt.args(arguments == null ? null : arguments.expressions);
		AbstractToken which = jtype.getWhich();
		EagleValue cls = which instanceof Javascript_Variable ? Javascript_Variable.evaluate(rt, (Javascript_Variable) which, null, 0)
				: rt.read(((com.eagle.tokens.TerminalToken) which).getValue());
		return rt.construct(cls, args, site);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushEagleValue(construct(JsRuntime.of(interpreter), jtype, null, this));
	}
}
