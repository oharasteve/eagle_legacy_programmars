// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractComment;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class RPGFree_Comment extends TerminalCommentToken implements AbstractComment
{
	// Need a default constructor for the parser
	public RPGFree_Comment()
	{
		this("");
	}

	public RPGFree_Comment(String comment, boolean hasEOLN)
	{
		super(comment, hasEOLN);
	}

	public RPGFree_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar + 1 >= nc) return false;
		if (rec.charAt(_currentChar) != '/') return false;

		char ch = rec.charAt(_currentChar + 1);
		if (ch != '*') return false;
		return super.possibleCommentPair2(lines, rec, "/*", "*/");
	}
	
	@Override
	public String description()
	{
		return "/* comment */";
	}
}
