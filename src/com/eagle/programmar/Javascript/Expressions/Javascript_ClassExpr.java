// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Class.Javascript_ClassElement;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_ClassExtends;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_ClassExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword CLASS = new Javascript_Keyword("class");
	public @S(20) @OPT Javascript_Class_Definition className;
	public @S(30) @OPT Javascript_ClassExtends extend;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) TokenList<Javascript_ClassElement> elements;
	public @S(60) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		String name = className != null && className.isPresent() ? className.getValue() : "anonymous";
		EagleValue existing = className != null && className.isPresent() ? rt.env.lookup(name) : null;
		if (existing instanceof JsValues.JsFunction && ((JsValues.JsFunction) existing).getTokenValue() == this) { interpreter.pushEagleValue(existing); return; }
		EagleValue cls = rt.makeClass(name, extend, elements, this);
		if (className != null && className.isPresent()) rt.env.define(name, cls);
		interpreter.pushEagleValue(cls);
	}
}
