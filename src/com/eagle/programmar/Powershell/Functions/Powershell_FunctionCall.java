// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Library;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement.Powershell_FunctionParam;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) @OPT Powershell_DiscardResult discardResult;
	public @S(20) @OPT Powershell_Library library;
	public @S(30) Powershell_Function_Reference funcRef;
	public @S(40) @OPT TokenList<Powershell_FunctionArg> arguments;

	public static class Powershell_DiscardResult extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Keyword VOID = new Powershell_Keyword("void");
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static class Powershell_FunctionArg extends TokenSequence
	{
		public @S(10) Powershell_Expression expr;
		public @S(20) @OPT PunctuationComma comma;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Is it one of the defined Functions?
		AbstractFunction fn = interpreter.findFunction(funcRef.getValue());
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + funcRef.getValue());
		}
		Powershell_FunctionStatement func = (Powershell_FunctionStatement) fn;
		String name = func.id.getValue();

		// Call the function
		// Make sure the function args match up
		int argCount = arguments.size();
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		ArrayList<String> argTypes = new ArrayList<String>();

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Powershell_Expression expr = arguments._elements.get(i).expr;
			Powershell_FunctionParam param = func.params.params.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.var.id.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Powershell_Element stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt.element);
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
