// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Basic_Comment extends TerminalCommentToken
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

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		// Grab the rest of the line
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}

	@Override
	public String description()
	{
		return "REM comment to end of line";
	}
}
