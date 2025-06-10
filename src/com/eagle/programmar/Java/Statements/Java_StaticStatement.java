// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2025

package com.eagle.programmar.Java.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;

public class Java_StaticStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT @NEWLINE Java_Keyword PRIVATE = new Java_Keyword("private");
	public @S(20) @OPT Java_Annotation annotation;
	public @S(30) @OPT Java_Keyword STATIC = new Java_Keyword("static");
	public @S(40) Java_Statement statement;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(statement);
	}
}
