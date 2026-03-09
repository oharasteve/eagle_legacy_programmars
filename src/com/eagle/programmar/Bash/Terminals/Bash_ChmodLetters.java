// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 8, 2026

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Bash_ChmodLetters extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int endChar = _currentChar;
		if (endChar >= recLen) return false;
		char ch;

		// all user group other
		while (endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (ch != 'a' && ch != 'u' && ch != 'g' && ch != 'o') break;
			endChar++;
		}

		// add remove replace
		ch = rec.charAt(endChar);
		if (ch != '+' && ch != '-' && ch != '=') return false;
		endChar++;

		// read write execute
		while (endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (ch != 'r' && ch != 'w' && ch != 'x') break;
			endChar++;
		}

		foundIt(_currentLine, endChar);
		_txt += rec.substring(_currentChar, endChar);
		return true;
	}
}

