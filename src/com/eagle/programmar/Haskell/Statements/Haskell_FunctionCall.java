// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Symbols.Haskell_Parameter_Definition;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Haskell_FunctionCall extends TokenSequence
	implements EagleRunnable // , EagleTransformableStatementList
{
	public @S(10) Haskell_Variable variable;
	public @S(20) Haskell_Punctuation arrow = new Haskell_Punctuation("<-");
	public @S(30) Haskell_Identifier_Reference funcName;
	public @S(40) @OPT TokenList<Haskell_Expression> arguments;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = funcName.getValue();

		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Haskell_Function func = (Haskell_Function) fn;

		// Make sure the function args match up
		int argCount = 0;
		if (arguments != null && arguments.isPresent())
		{
			argCount = arguments.size();
		}
		
		int paramCount = 0;
		if (func.definition.params != null && func.definition.params.isPresent())
		{
			paramCount = func.definition.params.size();
		}

		if (argCount != paramCount)
		{
			throw new RuntimeException("Function " + fnName + " expects #args = " +
					paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(fnName, func);

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			Haskell_Expression expr = arguments._elements.get(i);
			Haskell_Parameter_Definition param = func.definition.params._elements.get(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.getType());
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.tryToInterpret(func.definition.body);

		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Grab return value from stack and save it in the variable
		EagleValue val = interpreter.popEagleValue();
		interpreter.setSymbol(variable, variable.id.getValue(), val);

		// Now remove all those parameters
		interpreter.completedFunction(fnName, func);
	}
}
