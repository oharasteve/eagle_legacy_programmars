// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Statement;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Rexx_CallStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("instructions-call") Rexx_Keyword CALL = new Rexx_Keyword("CALL");
	public @S(20) Rexx_Identifier_Reference subName;
	public @S(30) @OPT SeparatedList<Rexx_Expression, PunctuationComma> args;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = subName.getValue();
		
		// Look up the subroutine
		Rexx_Function func = (Rexx_Function) interpreter.findFunction(name);
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}

		// Make sure the function args match up
		int argCount = 0;
		if (args != null)
		{
			argCount = args.getPrimaryCount();
		}
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Rexx_Expression expr = args.getPrimaryElement(i);
			Rexx_Variable_Definition param = func.params.params.getPrimaryElement(i);

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Rexx_Statement stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break; 
		}
		
		if (func._metrics == null)
		{
			func._metrics = new CallMetrics(interpreter._metrics, func.name.getValue(), this);
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
	}
}
