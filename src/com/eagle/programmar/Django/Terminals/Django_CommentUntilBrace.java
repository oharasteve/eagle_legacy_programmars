// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Django_CommentUntilBrace extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Django_CommentUntilBrace()
	{
		this("");
	}

	public Django_CommentUntilBrace(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		return commentUntilSentinel(lines, rec, "{%");
	}

	@Override
	public String description()
	{
		return "comment {%";
	}
}
