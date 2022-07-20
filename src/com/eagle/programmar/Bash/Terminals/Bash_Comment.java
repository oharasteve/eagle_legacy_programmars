// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Bash_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Bash_Comment()
	{
		this("");
	}
	
	public Bash_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		if (! super.possibleCommentToEndOfLine(rec, "#")) return false;
		
		// Check for shebang (#!) on line 1, columns 1-2
		if (_currentLine > 0) return true;
		if (rec.length() < 2) return true;
		if (rec.charAt(0) != '#' || rec.charAt(1) != '!') return true;
		
		// Dang, it is a she-bang (#!)
		return false;
	}
}
