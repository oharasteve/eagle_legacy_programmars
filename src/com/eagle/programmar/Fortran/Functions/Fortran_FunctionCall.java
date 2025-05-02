// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Statements.Fortran_Function;
import com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Fortran_Identifier_Reference variable;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Fortran_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = variable.getValue().toUpperCase();
		int argCount = args.getPrimaryCount();
		
//		// Check for built-in function names first
//		switch (fnName)
//		{
//		case "ADJUSTL":
//			if (argCount != 1)
//			{
//				throw new RuntimeException("ADJUSTL function requires 1 argument");
//			}
//			String str1 = interpreter.getStrValue(args.getPrimaryElement(0));
//			String trimmedStr = str1.stripLeading();
//			int lengthDifference = str1.length() - trimmedStr.length();
//			String newStr = trimmedStr + str1.substring(0, lengthDifference);
//			interpreter.pushStr(newStr);	// Left justifies a string, but keeps length same
//			return;
//		case "LEN":
//			if (argCount != 1)
//			{
//				throw new RuntimeException("LEN function requires 1 argument");
//			}
//			String str2 = interpreter.getStrValue(args.getPrimaryElement(0));
//			interpreter.pushInt(str2.length());
//			return;
//		case "MOD":
//			if (argCount != 2)
//			{
//				throw new RuntimeException("MOD function requires 2 arguments");
//			}
//			int numer = interpreter.getIntValue(args.getPrimaryElement(0));
//			int denom = interpreter.getIntValue(args.getPrimaryElement(1));
//			interpreter.pushInt(numer % denom);
//			return;
//		case "TRIM":
//			if (argCount != 1)
//			{
//				throw new RuntimeException("TRIM function requires 1 argument");
//			}
//			String str3 = interpreter.getStrValue(args.getPrimaryElement(0));
//			interpreter.pushStr(str3.stripTrailing());	// Only removes trailing spaces
//			return;
//		}
		
		// Check for subscripts second
		EagleValue var = interpreter.findSymbol(fnName);
		if (var != null && var.isArray() && argCount == 1)
		{
			EagleArray array = (EagleArray) var;
			int subscr = interpreter.getIntValue(args.getPrimaryElement(0));
			EagleValue val = array.getValue(subscr - 1);
			interpreter.pushEagleValue(val);
			return;
		}
		
		// Check for user functions third
		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null || !(fn instanceof Fortran_Function))
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Fortran_Function func = (Fortran_Function) fn;

		// Make sure the function args match up
		int paramCount = func.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, this, fnName);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Fortran_Expression expr = args.getPrimaryElement(i);
			Fortran_Variable_Reference param = func.parameters.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.typeName());
		}
		_metrics.called(argTypes);

		// Prepare to evaluate the procedure or function
		long startTime = System.nanoTime();

		// And transfer control to the procedure or function
		interpreter.callingFunction(fnName, func);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Fortran_Statement stmt : func.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// Need to put the result on the runtime stack
		// Fortran uses the function name for the return value
		// Sort-of like this: function sqrt(x) { sqrt = x*x }
		EagleValue val = interpreter.findSymbol(fnName);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
		}
		
		long elapsedTime = System.nanoTime() - startTime;
		if (func._metrics == null)
		{
			func._metrics = new CallMetrics(interpreter._metrics, fnName, func);
		}
		func._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(fnName, func);
	}
}