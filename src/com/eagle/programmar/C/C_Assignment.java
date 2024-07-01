// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class C_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) C_Expression expr;
	public @S(20) @OPT TokenList<C_MoreAssignments> more;

	public static class C_MoreAssignments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) C_Expression expr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
