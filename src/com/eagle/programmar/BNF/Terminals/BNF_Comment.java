// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2020

package com.eagle.programmar.BNF.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class BNF_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public BNF_Comment()
	{
		this("");
	}

	public BNF_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '(')
		{
			return super.possibleCommentPair2(lines, rec, "(*", "*)");
		}
		return false;
	}

	@Override
	public String description()
	{
		return "(* comment *)";
	}
}
