// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_Call_Statement extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword CALL = new Eaglish_Keyword("CALL");
	public @S(20) Eaglish_Identifier_Reference name;
	public @S(30) @OPT Eaglish_CallParameters callParams;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_CallParameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParenn;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> args;
		public @S(30) PunctuationRightParen rightParenn;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (interpreter._TRACE) System.err.println("*** Calling " + name);

		// Have to search for the FUNCTION definition
		Eaglish_Function_Block func = null;
		for (AbstractFunction absFn : interpreter._functionList)
		{
			Eaglish_Function_Block fn = (Eaglish_Function_Block) absFn;
			if (fn.var.getValue().equalsIgnoreCase(name.getValue()))
			{
				// Found it!
				func = fn;
				break;
			}
		}

		if (func == null)
		{
			throw new RuntimeException("Unable to find a FUNCTION named " + name);
		}

		// Count the parameters
		int expected = func.parameterStatements.size();
		int actual = callParams.args.getPrimaryCount();
		if (actual != expected)
		{
			throw new RuntimeException(
					"Function " + name + ", expected params = " + expected + ", but actual args = " + actual);
		}

		// Assign all the parameters
		for (int i = 0; i < actual; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
			// interpreter.tryToInterpret(arg);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.param.getValue(), val);
		}

		// Evaluate the function
		long startTime = System.nanoTime();
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			interpreter.tryToInterpret(stmt.getWhich());
		}
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Remove all the parameters
		for (int i = 0; i < actual; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			interpreter._symbolTable.removeSymbols(param.param.getValue());
		}
	}
}
