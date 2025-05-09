// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.tokens.terminals.TerminalCommentRestOfLineToken;

public class Basic_Comment extends TerminalCommentRestOfLineToken
{
	// Need a default constructor for the parser
	public Basic_Comment()
	{
		this("");
	}

	public Basic_Comment(String comment)
	{
		super(comment);
	}
}
