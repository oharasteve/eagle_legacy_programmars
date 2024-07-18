// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Terminals.Scala_Comment;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.tokens.TokenSequence;

public class Scala_CommentEoln extends TokenSequence implements EagleRunnable
{
	public @S(10) Scala_Comment comment;
	public @S(20) Scala_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
	}
}