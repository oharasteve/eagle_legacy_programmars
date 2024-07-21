// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Statements.Go_Function;
import com.eagle.programmar.Go.Statements.Go_Function.Go_FunctionParamater;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_FunctionCall extends PrimaryOperator implements EagleRunnable, AbstractStatement
{
	public @S(10) Go_Variable funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Go_Expression, PunctuationComma> arguments;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = funcName.vars.first().getValue();
		Go_Function func = null;
		
		for (AbstractFunction fn : interpreter._functionList)
		{
			if (fn instanceof Go_Function)
			{
				Go_Function f = (Go_Function) fn;
				if (f.id.getValue().equals(fnName))
				{
					func = f;
					break;
				}
			}
		}
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}

		// Make sure the function args match up
		int argCount = arguments.getPrimaryCount();

		int paramCount = 0;
		if (func.parameters != null && func.parameters.isPresent())
		{
			paramCount = func.parameters.getPrimaryCount();
		}
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		if (argCount > 0)
		{
			for (int i = 0; i < argCount; i++)
			{
				Go_Expression expr = arguments.getPrimaryElement(i);
				Go_FunctionParamater param = func.parameters.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						param.var.getValue(), val);
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.tryToInterpret(func.stmt);

		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		if (argCount > 0)
		{
			for (int i = 0; i < argCount; i++)
			{
				Go_FunctionParamater param = func.parameters.getPrimaryElement(i);
				interpreter._symbolTable.removeSymbols(param.var.getValue());
			}
		}
	}
}
