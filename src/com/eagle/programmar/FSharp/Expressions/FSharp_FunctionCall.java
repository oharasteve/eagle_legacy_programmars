// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Statements.FSharp_Function;
import com.eagle.programmar.FSharp.Statements.FSharp_Function.FSharp_FunctionParam;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class FSharp_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) FSharp_Variable functionName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<FSharp_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Look up the function in our function list
		String name = functionName.id.getValue();
		AbstractFunction fn = interpreter._functionList.get(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		FSharp_Function func = (FSharp_Function) fn;

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = func.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			FSharp_Expression expr = argList.getPrimaryElement(i);
			FSharp_FunctionParam param = func.params.getPrimaryElement(i);

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.var.getValue(), val);
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (FSharp_Statement stmt : func.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt.statementOrComment);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			FSharp_FunctionParam param = func.params.getPrimaryElement(i);
			interpreter._symbolTable.removeSymbols(param.var.getValue());
		}
	}
}
