// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Haskell_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Haskell_Comment()
	{
		this("");
	}

	public Haskell_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '-')
		{
			return super.possibleCommentToEndOfLine(rec, "--");
		}
		return false;
	}

	@Override
	public String description()
	{
		return "-- comment to end of line";
	}
}
