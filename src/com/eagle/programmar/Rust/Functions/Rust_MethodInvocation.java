// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Function;
import com.eagle.programmar.Rust.Rust_Function.Rust_Parameter;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_MethodInvocation extends PrimaryOperator implements EagleRunnableWithResult
{
	public @S(10) Rust_Variable methodName;
	public @S(20) @OPT Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		String name = methodName.var.getValue();

		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Rust_Function func = (Rust_Function) fn;

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = func.funcParamDefs.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, methodName.var, name);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Rust_Expression arg = argList.getPrimaryElement(i);
			Rust_Parameter param = func.funcParamDefs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, param.var.getValue(), val);
			argTypes.add(val.typeName());
		}
		_metrics.called(argTypes);

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.callingFunction(name, func);
		Eagle_Statement_Result result = interpreter.tryToInterpret(func.stmt);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);

		return result;
	}
}
