// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalPictureToken;

public class PLI_Picture extends TerminalPictureToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch1 = rec.charAt(_currentChar);
		if (ch1 != '\'')
		{
			return false;
		}
		
		_endChar = _currentChar + 1;
		while (_endChar < recLen)
		{
			char ch2 = rec.charAt(_endChar);
			if (ch2 == '\'')
			{
				break;
			}
			_endChar++;
		}

		foundIt(_currentLine, _endChar);
		_pic = rec.substring(_currentChar, _endChar + 1);
		return true;
	}

	@Override
	public String description()
	{
		return "A PL/I PICTURE, such '99V99' or 'X(10)'";
	}
}
