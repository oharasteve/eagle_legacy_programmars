// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Statement;
import com.eagle.programmar.Rexx.Statements.Rexx_Function;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rexx_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Rexx_Identifier_Reference fnName;
	public @S(20) Rexx_FnCallArguments callArguments;

	public static class Rexx_FnCallArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Rexx_Expression, PunctuationComma> args;
		public @S(30) PunctuationRightParen rightParen;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();
		
		// See if it is a subscripted variable first
		EagleValue value = interpreter.findSymbol(name);
		if (value != null && value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int index = interpreter.getIntValue(callArguments.args.first());
			interpreter.pushEagleValue(array.getValue(index));
			return;
		}
		
		// Look up the function
		Rexx_Function func = (Rexx_Function) interpreter.findFunction(name);
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}

		// Make sure the function args match up
		int argCount = callArguments.args.getPrimaryCount();
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, this, name);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Rexx_Expression expr = callArguments.args.getPrimaryElement(i);
			Rexx_Variable_Definition param = func.params.params.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.typeName());
		}
		_metrics.called(argTypes);

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Rexx_Statement stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break; 
		}
		
		// Need to put the result on the runtime stack
		// Rexx uses the function name for the return value
		// Sort-of like this: Function sqrt(x) ; sqrt = x*x ; End Function
		EagleValue val = interpreter.findSymbol(name);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
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
