// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Scala_FunctionCall extends TokenSequence implements EagleRunnable
{
	public @S(10) Scala_Variable func;
	public @S(20) @OPT Scala_FunctionArguments args;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) Scala_EOLN eoln;

	public static class Scala_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Scala_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume System.out.println(expr.exp);
		EagleValue result = interpreter.getEagleValue(args.arguments.first());
		System.out.println(result.toString());
	}
}
