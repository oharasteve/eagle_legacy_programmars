// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Go;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Terminals.Go_Comment;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenSequence;

public class Go_CommentEoln extends TokenSequence implements EagleRunnable
{
	public @S(10) Go_Comment comment;
	public @S(20) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}