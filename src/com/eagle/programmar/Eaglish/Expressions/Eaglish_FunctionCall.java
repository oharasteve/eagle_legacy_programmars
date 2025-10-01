// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
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
	public @S(10) Eaglish_Identifier_Reference fnName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Eaglish_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Have to search for the FUNCTION definition
		String name = fnName.getValue();
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Eaglish_Function_Block func = (Eaglish_Function_Block) fn;

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
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Expression arg = args.getPrimaryElement(i);
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, param.param.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.callingFunction(name, func);
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
	}
}
