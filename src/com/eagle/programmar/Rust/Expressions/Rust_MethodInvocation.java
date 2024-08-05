// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
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

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		String name = methodName.var.getValue();
		if (interpreter._TRACE) System.err.println("*** Calling " + name + "()");

		AbstractFunction fn = interpreter._functionList.get(name);
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

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Rust_Expression arg = argList.getPrimaryElement(i);
			Rust_Parameter param = func.funcParamDefs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.var.getValue(), val);
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		Eagle_Statement_Result result = interpreter.tryToInterpret(func.stmt);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Rust_Parameter param = func.funcParamDefs.getPrimaryElement(i);
			interpreter._symbolTable.removeSymbols(param._name);
		}

		return result;
	}
}
