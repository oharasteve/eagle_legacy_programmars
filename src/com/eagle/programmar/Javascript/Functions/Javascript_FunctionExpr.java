// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Javascript.Javascript_FunctionValues;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_FunctionExpr extends PrimaryOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Function function;
	public @S(20) @OPT Javascript_ParenthesizedExpression args;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		String name = function.implementation.id != null && function.implementation.id.isPresent() ? function.implementation.id.getValue() : "anonymous";
		JsValues.JsFunction f = new JsValues.JsFunction(name, this, rt.env);
		if (args != null && args.isPresent()) interpreter.pushEagleValue(rt.call(f, JsValues.undefined(), rt.args(args.expressions), this));
		else interpreter.pushEagleValue(f);
	}

	/** A function value: the generator's closure (Rust: |x: i32| x * 2). */
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return Javascript_FunctionValues.closure(transformer, generator, Javascript_FunctionValues.shape(this), this);
	}
}
