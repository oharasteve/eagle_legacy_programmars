// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, June 17, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class PLI_Literal extends TerminalLiteralToken
{
	public PLI_Literal()
	{
		super("'", false, '?', true, false);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		if (rec.endsWith("'B") || rec.endsWith("'X")) // Binary or Hex literals
		{
			return false;
		}
		return super.parse(lines);
	}
}
