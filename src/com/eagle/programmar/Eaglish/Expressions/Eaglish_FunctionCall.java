// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Function_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Parameter_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_Identifier_Reference name;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Eaglish_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (interpreter._TRACE) System.err.println("*** Calling " + name + "()");

		// Have to search for the FUNCTION definition
		Eaglish_Function_Block func = null;
		for (AbstractFunction absFn : interpreter._functionList)
		{
			Eaglish_Function_Block fn = (Eaglish_Function_Block) absFn;
			if (fn.var.getValue().equalsIgnoreCase(name.getValue()))
			{
				// Found it!
				func = fn;
				break;
			}
		}

		if (func == null)
		{
			throw new RuntimeException("Unable to find a FUNCTION named " + name);
		}

		// Make sure the function args match up
		if (!func.returnsStatement.isPresent())
		{
			throw new RuntimeException("Function " + name + " doesn't return any value");
		}
		int argCount = args.getPrimaryCount();
		int paramCount = func.parameterStatements.size();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Expression arg = args.getPrimaryElement(i);
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.param.getValue(), val);
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			interpreter._symbolTable.removeSymbols(param._name);
		}
	}
}
