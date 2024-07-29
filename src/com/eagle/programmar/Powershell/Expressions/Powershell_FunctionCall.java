// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Library;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement.Powershell_FunctionParam;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) @OPT Powershell_DiscardResult discardResult;
	public @S(20) @OPT Powershell_Library library;
	public @S(30) Powershell_Function_Reference funcRef;
	public @S(40) @OPT TokenList<Powershell_Expression> arguments;

	public @SKIP CallMetrics _metrics = null;

	public static class Powershell_DiscardResult extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Keyword VOID = new Powershell_Keyword("void");
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Is it a library call?
		if (library != null && library.isPresent())
		{
			if (library.name.first().getValue().equalsIgnoreCase("Math"))
			{
				if (funcRef.getValue().equalsIgnoreCase("Floor"))
				{
					int val = interpreter.getIntValue(arguments._elements.get(0));
					interpreter.pushInt(val);
					return;
				}
			}
		}
		
		// Is it one of the defined Functions?
		for (AbstractFunction absFn : interpreter._functionList)
		{
			Powershell_FunctionStatement func = (Powershell_FunctionStatement) absFn;
			if (func.name.getValue().equals(funcRef.getValue()))
			{
				if (_metrics == null)
				{
					_metrics = new CallMetrics(interpreter._metrics, funcRef.getValue(), getFileName(), getStartLine(),
							getStartChar());
				}
				
				// Call the function
				if (interpreter._TRACE) System.err.println("**** Calling " + func.name.getValue());

				// Make sure the function args match up
				int argCount = arguments.size();
				int paramCount = func.params.params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new RuntimeException(
							"Function " + func.name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				// Now assign all the parameters
				for (int i = 0; i < argCount; i++)
				{
					Powershell_Expression expr = arguments._elements.get(i);
					Powershell_FunctionParam param = func.params.params.getPrimaryElement(i);

					EagleValue val = interpreter.getEagleValue(expr);
					interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
							param.var.id.getValue(), val);
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (Powershell_Statement stmt : func.stmts._elements)
				{
					result = interpreter.tryToInterpret(stmt.element);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}

				// The result was already put on the runtime stack
				long elapsedTime = System.nanoTime() - startTime;
				func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

				// Now remove all those parameters
				for (int i = 0; i < argCount; i++)
				{
					Powershell_FunctionParam param = func.params.params.getPrimaryElement(i);
					interpreter._symbolTable.removeSymbols(param.var.id.getValue());
				}
				return;
			}
		}
		
		throw new RuntimeException("Unable to find a Function named " + funcRef.getValue());
	}
}
