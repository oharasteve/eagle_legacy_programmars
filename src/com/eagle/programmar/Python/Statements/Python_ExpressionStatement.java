// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.tokens.TokenSequence;

public class Python_ExpressionStatement extends TokenSequence
{
	public @NOSPACE Python_Expression expression;
	public @OPT Python_Comment comment;
}
