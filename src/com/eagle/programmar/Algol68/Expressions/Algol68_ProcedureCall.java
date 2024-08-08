// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure.Algol68_Parameter;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Algol68_ProcedureCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Algol68_Variable procName;
	public @S(20) @OPT Algol68_Punctuation question = new Algol68_Punctuation("?");
	public @S(30) Algol68_FunctionArguments args;

	public static class Algol68_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Algol68_FunctionArg, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;

	}

	public static class Algol68_FunctionArg extends TokenChooser
	{
		public @CHOICE Algol68_Expression XXexpr;

		public @CHOICE static class Algol68_FunctionSetArg extends TokenSequence
		{
			public @S(10) Algol68_Identifier_Reference id;
			public @S(20) Algol68_Punctuation arrow = new Algol68_Punctuation("=>");
			public @S(30) Algol68_Expression expr;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Algol68_Identifier_Reference id = procName.vars.first();
		if (interpreter._TRACE) System.err.println("*** Calling " + id + "()");

		// Have to search for the PROC definition
		AbstractFunction fn = interpreter.findFunction(id.getValue());
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a procedure named " + id.getValue());
		}
		Algol68_Procedure proc = (Algol68_Procedure) fn;

		// Make sure the function args match up
		int argCount = args.arguments.getPrimaryCount();
		int paramCount = proc.params.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Proc " + id.getValue() + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Algol68_FunctionArg arg = args.arguments.getPrimaryElement(i);
			Algol68_Parameter param = proc.params.parameters.getPrimaryElement(i);
			AbstractToken which = arg.getWhich();
			if (which instanceof Algol68_Expression)
			{
				Algol68_Expression expr = (Algol68_Expression) which;
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.param.getValue(), val);
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function / procedure
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		// EagleValue returnValue = null;
		for (Algol68_Statement stmt : proc.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
			// returnValue = interpreter.getStackTop();		// Really weird. No "return" statement
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		proc._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Algol68_Parameter param = proc.params.parameters.getPrimaryElement(i);
			interpreter.removeSymbols(param.param.getValue());
		}
	}
}

