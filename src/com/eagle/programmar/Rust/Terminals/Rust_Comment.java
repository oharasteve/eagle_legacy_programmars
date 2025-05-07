// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractComment;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class Rust_Comment extends TerminalCommentToken implements AbstractComment
{
	// Need a default constructor for the parser
	public Rust_Comment()
	{
		this("");
	}

	public Rust_Comment(String comment, boolean hasEOLN)
	{
		super(comment, hasEOLN);
	}

	public Rust_Comment(String comment)
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
		switch (ch)
		{
		case '/':
			return super.possibleCommentToEndOfLine(rec, "//");
		case '*':
			return super.possibleCommentPair2(lines, rec, "/*", "*/");
		}
		return false;
	}

	@Override
	public String description()
	{
		return "/* comment */ or // comment to end of line";
	}
}
