// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenSequence;

public class Eaglish_CommentEoln extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Comment comment;
	public @S(20) @OPT Eaglish_EndOfLine eoln;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do
	}
}