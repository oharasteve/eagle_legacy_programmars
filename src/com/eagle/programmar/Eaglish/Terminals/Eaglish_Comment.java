// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractComment;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Eaglish_Comment extends TerminalCommentToken implements AbstractComment
{
	public Eaglish_Comment()
	{
		this("");
	}

	public Eaglish_Comment(String comment, boolean hasEOLN)
	{
		super(comment, hasEOLN);
	}

	public Eaglish_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar >= nc) return false;
		if (rec.charAt(_currentChar) != '#') return false;

		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}

	@Override
	public String description()
	{
		return "# comment to end of line";
	}
}
