// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_ClassCreationExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
	public @S(20) Javascript_Type jtype;
	public @S(25) @OPT @NOSPACE com.eagle.programmar.Javascript.TypeScript.TS_TypeArguments typeArguments;
	public @S(30) Javascript_ParenthesizedExpression arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		interpreter.pushEagleValue(Javascript_NewNoArgsExpression.construct(rt, jtype, arguments, this));
	}
}
