// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 8, 2026

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CMacro_TextLine extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// Don't allow C lines to start with a #
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (recLen < _currentChar) return false;
		if (_currentChar < recLen && rec.charAt(_currentChar) == '#')
		{
			// Check to make sure we are at the start of a line. This check may be
			// superfluous.
			// Normally, _currentChar = 0 for a macro line that starts with #
			// In that case, the loop doesn't even execute once so it fails as a text line.
			boolean atStart = true;
			for (int i = 0; i < _currentChar; i++)
			{
				char ch = rec.charAt(i);
				if (ch != ' ' && ch != '\t')
				{
					atStart = false;
					break;
				}
			}
			if (atStart) return false;
		}

		foundIt(_currentLine, recLen);
		_txt = rec.substring(_currentChar, recLen);
		return true;
	}

	@Override
	public String description()
	{
		return "macro text line";
	}
}
