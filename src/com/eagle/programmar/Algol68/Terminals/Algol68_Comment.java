// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.terminals.TerminalCommentToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_Comment extends TerminalCommentToken
		implements EagleTransformableStatement
{
	public Algol68_Comment()
	{
		this("");
	}

	public Algol68_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		return super.possibleCommentPair1(lines, rec, '#', '#');
	}

	@Override
	public String description()
	{
		return "# comment #";
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		return null;		// Might want to keep comment statements somehow.
	}
}
