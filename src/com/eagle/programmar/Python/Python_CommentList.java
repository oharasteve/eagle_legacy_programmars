// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2022

package com.eagle.programmar.Python;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;

public class Python_CommentList extends TokenSequence implements EagleRunnable
{
	public @S(10) @NOSPACE SeparatedList<Python_Comment, Python_EndOfLine> comments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do
	}
}
