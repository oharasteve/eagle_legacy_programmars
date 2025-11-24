// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 7, 2025

package com.eagle.programmar.Ruby;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ruby.Terminals.Ruby_Comment;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ruby_CommentEoln extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Ruby_Comment comment;
	public @S(20) Ruby_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		return null; // Might want to keep comment statements somehow.
	}
}
