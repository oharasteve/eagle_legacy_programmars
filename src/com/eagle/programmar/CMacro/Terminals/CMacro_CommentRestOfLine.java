// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 18, 2022

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.terminals.TerminalCommentRestOfLineToken;

public class CMacro_CommentRestOfLine extends TerminalCommentRestOfLineToken
{
	// Need a default constructor for the parser
	public CMacro_CommentRestOfLine()
	{
		this("");
	}

	public CMacro_CommentRestOfLine(String comment)
	{
		super(comment);
	}
}
