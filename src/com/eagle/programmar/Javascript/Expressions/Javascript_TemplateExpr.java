// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Reference;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_TemplateLiteral;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_TemplateExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) @OPT Javascript_Function_Reference func;
	public @S(20) Javascript_TemplateLiteral template;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushEagleValue(JsRuntime.of(interpreter).template(template.getValue()));
	}
}
