// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Params.Python_Parameter;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Function_Call extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable fnName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT @SYNTAX(Python_Multiline_Syntax.class) SeparatedList<Python_Expression, PunctuationComma> argList;
	public @S(40) @OPT PunctuationComma extraComma;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = "unknown";
		if (fnName.var.getWhich() instanceof Python_Identifier_Reference)
		{
			Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
			name = id.getValue();
		}

		// Look up the function in our function list
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Python_FunctionDefinition func = (Python_FunctionDefinition) fn;

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = 1;
		if (func.header.params.params.moreParams != null && func.header.params.params.moreParams.isPresent())
		{
			paramCount += func.header.params.params.moreParams.size();
		}
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		Python_Parameter param = func.header.params.params.param;
		for (int i = 0; i < argCount; i++)
		{
			Python_Expression expr = argList.getPrimaryElement(i);
			if (i > 0)
			{
				param = func.header.params.params.moreParams._elements.get(i-1).param;
			}
			if (param.getWhich() instanceof Python_Variable)
			{
				Python_Variable var = (Python_Variable) param.getWhich();
				if (var.var.getWhich() instanceof Python_Identifier_Reference)
				{
					Python_Identifier_Reference ref = (Python_Identifier_Reference) var.var.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, ref.getValue(), val);
				}
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.callingFunction(name, func.header);
		interpreter.tryToInterpret(func.header.defBody);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(name, func.header);
	}
}
