// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_Call_Statement extends TokenSequence implements EagleRunnable, AbstractStatement
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
		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(name.getValue());
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name.getValue());
		}
		Eaglish_Function func = (Eaglish_Function) fn;

		// Count the parameters
		int expected = func.parameterStatements.size();
		int actual = callParams.args.getPrimaryCount();
		if (actual != expected)
		{
			throw new RuntimeException(
					"Function " + name + ", expected params = " + expected + ", but actual args = " + actual);
		}

		// Assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < actual; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
			// interpreter.tryToInterpret(arg);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, param.param.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Evaluate the function
		long startTime = System.nanoTime();
		interpreter.callingFunction(name.getValue(), func);
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Remove all the parameters
		interpreter.completedFunction(name.getValue(), func);
	}
}
