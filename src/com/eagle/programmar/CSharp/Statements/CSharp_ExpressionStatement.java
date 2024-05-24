// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @NEWLINE CSharp_Expression expr;
	public @S(20) @NOSPACE PunctuationSemicolon semicolon;
	public @S(30) @OPT CSharp_Comment comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
