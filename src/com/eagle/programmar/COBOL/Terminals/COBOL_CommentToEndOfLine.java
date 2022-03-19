// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.tokens.TerminalCommentRestOfLineToken;

public class COBOL_CommentToEndOfLine extends TerminalCommentRestOfLineToken
{
	public COBOL_CommentToEndOfLine()
	{
		this("");
	}

	public COBOL_CommentToEndOfLine(String comment)
	{
		super(comment);
	}
}
