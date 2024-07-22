// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ruby_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ruby_Variable funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Ruby_Expression, PunctuationComma> arguments;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference id = funcName.vars.first();
		String name = id.getValue();
		
		// Look up the function
		Ruby_Function func = null;
		for (AbstractFunction token : interpreter._functionList)
		{
			Ruby_Function fn = (Ruby_Function) token;
			if (fn.id.getValue().equals(name))
			{
				func = fn;
				break;
			}
		}
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}

		// Make sure the function args match up
		int argCount = arguments.getPrimaryCount();
		int paramCount = func.params.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Ruby_Expression expr = arguments.getPrimaryElement(i);
			Ruby_Variable param = func.params.parameters.getPrimaryElement(i);

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.vars.first().getValue(), val);
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Ruby_Statement stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Ruby_Variable param = func.params.parameters.getPrimaryElement(i);
			interpreter._symbolTable.removeSymbols(param.vars.first().getValue());
		}
	}
}
