// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Javascript_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '`')
		{
			// backticks can span multiple lines, and can inject values with $(x) inside
			return genericLiteral(lines, "`", true, '\\', false, true);
		}

		return genericLiteral(lines, "\"'", true, '\\', false, false);
	}
	
	@Override
	public String description()
	{
		return "literal, depends on back tick (`)";
	}
}
