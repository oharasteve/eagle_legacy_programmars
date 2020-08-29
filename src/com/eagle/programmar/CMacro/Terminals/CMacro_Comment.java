// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class CMacro_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public CMacro_Comment()
	{
		this("");
	}
	
	public CMacro_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (nc < 3 || _currentChar != 0) return false;
		if (rec.charAt(_currentChar) != '#') return false;
		if (rec.charAt(_currentChar+1) != ' ') return false;

		return super.possibleCommentToEndOfLine(rec, "#");
	}
}
