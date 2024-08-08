// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Function;
import com.eagle.programmar.AWK.Terminals.AWK_Identifier;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_UserFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Identifier functionName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (interpreter._TRACE) System.err.println("*** Calling " + functionName + "()");

		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(functionName.getValue());
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + functionName.getValue());
		}
		AWK_Function func = (AWK_Function) fn;

		// Doesn't do much, just set metrics
		interpreter.tryToInterpret(func);

		// Make sure the function args match up
		int argCount = 0;
		if (argList != null && argList.isPresent())
		{
			argCount = 1;
			if (argList.more != null) argCount += argList.more.size();
			int paramCount = 1;
			if (func.parameters.moreParams != null) paramCount += func.parameters.moreParams.size();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + functionName + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			AWK_Expression arg = argList.expr;
			AWK_Identifier param = func.parameters.param;
			for (int i = 0; i < argCount; i++)
			{
				if (i > 0)
				{
					arg = argList.more._elements.get(i - 1).expr;
					param = func.parameters.moreParams._elements.get(i - 1).param;
				}
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						param.getValue(), val);
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		for (AWK_StatementOrComment stmt : func.body.elements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		if (argList != null && argList.isPresent())
		{
			// Now remove all those parameters
			AWK_Identifier param = func.parameters.param;
			interpreter.removeSymbols(param._name);
			for (int i = 1; i < argCount; i++)
			{
				param = func.parameters.moreParams._elements.get(i - 1).param;
				interpreter.removeSymbols(param._name);
			}
		}
	}
}
