// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 16, 2024

package com.eagle.programmar.SQLite.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class SQLite_MultilineString extends TerminalLiteralToken
{
	public SQLite_MultilineString()
	{
		super("\"'`", true, '\\', false, true);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		// Check for <<STOPPER lines STOPPER
		EagleLineReader rec = lines.get(_currentLine);
		if (multilineStopper(lines, rec, "<<")) return true;

		return super.parse(lines);
	}
}
