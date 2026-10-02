// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.generate.BuiltInEnum;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_BuiltInVar extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_KeywordChoice builtinConstant = new Javascript_KeywordChoice("arguments", "false", "null",
			"String", "super", "this", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.getValue())
		{
		case "true": interpreter.pushBool(true); return;
		case "false": interpreter.pushBool(false); return;
		case "null": interpreter.pushEagleValue(JsValues.nul()); return;
		case "undefined": interpreter.pushEagleValue(JsValues.undefined()); return;
		case "NaN": interpreter.pushEagleValue(JsValues.num(Double.NaN)); return;
		case "Infinity": interpreter.pushEagleValue(JsValues.num(Double.POSITIVE_INFINITY)); return;
		case "this": interpreter.pushEagleValue(JsRuntime.of(interpreter).thisValue()); return;
		case "arguments": interpreter.pushEagleValue(JsValues.undefined()); return;
		default: interpreter.pushEagleValue(JsRuntime.of(interpreter).read(builtinConstant.getValue()));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		switch (builtinConstant.toString().toLowerCase())
		{
		case "false":
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		case "true":
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
		}
	}
}
