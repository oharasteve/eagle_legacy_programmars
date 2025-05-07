// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractComment;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Python_Comment extends TerminalCommentToken implements AbstractComment
{
	public Python_Comment()
	{
		this("");
	}

	public Python_Comment(String comment, boolean hasEOLN)
	{
		super(comment, hasEOLN);
	}

	public Python_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) != FOUND.GOOD) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar < nc)
		{
			char ch = rec.charAt(_currentChar);

			if (ch == '#')
			{
				foundIt(_currentLine, nc);
				_comment = rec.substring(_currentChar, nc);
				return true;
			}

			if (ch == '/')
			{
				return super.possibleCommentPair2(lines, rec, "/*", "*/");
			}
		}

		return false;
	}

	@Override
	public String description()
	{
		return "/* comment */ or # comment to end of line";
	}
}
