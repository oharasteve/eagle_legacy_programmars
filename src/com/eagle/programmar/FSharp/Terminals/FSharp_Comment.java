// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractComment;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class FSharp_Comment extends TerminalCommentToken implements AbstractComment
{
	public FSharp_Comment()
	{
		this("");
	}

	public FSharp_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) != FOUND.GOOD) return false;
		EagleLineReader rec = lines.get(_currentLine);
		return possibleCommentToEndOfLine(rec, "//");
	}

	@Override
	public String description()
	{
		return "// comment to end of line";
	}
}
