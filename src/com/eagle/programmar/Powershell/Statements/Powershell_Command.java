// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement.Powershell_FunctionParam;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_VerbNoun;
import com.eagle.programmar.Powershell.Terminals.Powershell_Word;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Powershell_Command extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) Powershell_WhichCommand which; // Like Get-Content or javac
	public @S(20) @OPT TokenList<Powershell_CommandArg> argList;

	public @SKIP CallMetrics _metrics = null;

	public static class Powershell_WhichCommand extends TokenChooser
	{
		public @CHOICE PunctuationAmpersand XXampersand;
		public @CHOICE PunctuationPeriod XXdot;
		public @CHOICE Powershell_Punctuation XXdotDot = new Powershell_Punctuation("..");
		public @CHOICE PunctuationColon XXcolon;
		public @CHOICE Powershell_VerbNoun XXverbNoun; // Like Get-Content for example
		public @LAST Powershell_Function_Reference XXid;
	}

	public static class Powershell_CommandArg extends TokenSequence
	{
		public @S(10) Powershell_CommandOneArg arg;
		public @S(20) @OPT PunctuationComma comma;

		public static class Powershell_CommandOneArg extends TokenChooser
		{
			public @FIRST Powershell_Filename XXfilename; // Tricky because the dot is already taken
			public @CHOICE Powershell_Expression XXexpr;
			public @LAST Powershell_Word XXword;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (which.getWhich() instanceof Powershell_Function_Reference)
		{
			Powershell_Function_Reference fnName = (Powershell_Function_Reference) which.getWhich();
			
			// Is it one of the defined Functions?
			for (AbstractFunction absFn : interpreter._functionList)
			{
				Powershell_FunctionStatement func = (Powershell_FunctionStatement) absFn;
				if (func.name.getValue().equals(fnName.getValue()))
				{
					if (_metrics == null)
					{
						_metrics = new CallMetrics(interpreter._metrics, fnName.getValue(), getFileName(), getStartLine(),
								getStartChar());
					}
					
					// Call the function
					if (interpreter._TRACE) System.err.println("**** Calling " + func.name.getValue());

					// Make sure the function args match up
					int argCount = argList.size();
					int paramCount = func.params.params.getPrimaryCount();
					if (argCount != paramCount)
					{
						throw new RuntimeException(
								"Function " + func.name + " expects #args = " + paramCount + ", but was given " + argCount);
					}

					// Now assign all the parameters
					for (int i = 0; i < argCount; i++)
					{
						Powershell_CommandArg arg = argList._elements.get(i);
						Powershell_FunctionParam param = func.params.params.getPrimaryElement(i);

						if (arg.arg.getWhich() instanceof Powershell_Expression)
						{
							Powershell_Expression expr = (Powershell_Expression) arg.arg.getWhich();
							EagleValue val = interpreter.getEagleValue(expr);
							interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
									param.var.id.getValue(), val);
						}
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
			
			throw new RuntimeException("Unable to find a Function named " + fnName.getValue());
		}
	}
}
