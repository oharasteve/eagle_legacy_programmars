// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Go;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Terminals.Go_Comment;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_CommentEoln extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Go_Comment comment;
	public @S(20) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		return null;		// Might want to keep comment statements somehow.
	}
}