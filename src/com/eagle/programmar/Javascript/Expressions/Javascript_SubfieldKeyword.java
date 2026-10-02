// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: a member whose name is a reserved word, with or
// without a call: promise.then(f).catch(g), module.default, obj.delete(k). Javascript_Subfield
// is tried first and takes every ordinary name; this one only matches what it refuses.

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_SubfieldKeyword extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice dot = new Javascript_PunctuationChoice(".", "?.");
	public @S(30) Javascript_Field_Reference field;
	public @S(40) @OPT Javascript_ParenthesizedExpression arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue target = rt.eval(left);
		if ("?.".equals(dot.getValue()) && JsValues.isNullish(target)) { interpreter.pushEagleValue(JsValues.undefined()); return; }
		if (arguments != null && arguments.isPresent())
			interpreter.pushEagleValue(rt.callMethod(target, field.getValue(), rt.args(arguments.expressions), this));
		else
			interpreter.pushEagleValue(rt.getProperty(target, field.getValue(), false));
	}
}
