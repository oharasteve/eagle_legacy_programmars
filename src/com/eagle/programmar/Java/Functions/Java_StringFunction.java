// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2025

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Java_StringFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Keyword STRING = new Java_Keyword("String");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_StringChoice choice;

	public static class Java_StringChoice extends TokenChooser
	{
		public @CHOICE Java_StringFormatFunc XXstringFormatFunction;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(choice);
	}

	public static Java_Expression wrapStringFunction(AbstractToken choice,
			AbstractToken source)
	{
		Java_StringFunction func = new Java_StringFunction();
		func.dot = new PunctuationPeriod();
		func.choice = new Java_StringChoice();
		func.choice.setWhich(choice);
		func.setTransformationSource(source);
		return Java_Generator.wrapExpression(func);
	}
}
