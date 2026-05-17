// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 15, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Terminals.Haskell_Comment;
import com.eagle.tokens.TokenSequence;

public class Haskell_CommentStatement extends TokenSequence
{
	public @S(10) Haskell_Comment comment;
}
