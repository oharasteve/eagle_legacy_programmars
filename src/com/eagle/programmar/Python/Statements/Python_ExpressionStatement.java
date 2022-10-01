// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @NOSPACE Python_Expression expression;
	public @S(20) @OPT Python_ExpressionType type;
	public @S(30) @OPT Python_Comment comment;
	
	public static class Python_ExpressionType extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationColon colon;
		public @S(20) Python_Type type;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}
}
