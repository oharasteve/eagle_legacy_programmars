// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 19, 2022

package com.eagle.programmar.CPlus.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.C.Terminals.C_Literal;

public class CPlus_Literal extends C_Literal
{
	private static final String[] PREFIXES = new String[] {
			"u8R", "u8", "u", "L", "R", "LR"
	};

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();

		// Pick up the prefix(es), if they are present
		char pre1 = ' ';
		char pre2 = ' ';
		char pre3 = ' ';
		if (_currentChar < nc) pre1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) pre2 = rec.charAt(_currentChar + 1);
		if (_currentChar + 2 < nc) pre3 = rec.charAt(_currentChar + 2);

		int prefixLen = 0;
		for (String prefix : PREFIXES)
		{
			int len = prefix.length();
			if (len >= 3 && pre3 != prefix.charAt(2)) continue;
			if (len >= 2 && pre2 != prefix.charAt(1)) continue;
			if (len >= 1 && pre1 != prefix.charAt(0)) continue;

			prefixLen = len;
			_currentChar += prefixLen;
			lines.setCurrentChar(_currentChar);
			lines.setCurrentLine(_currentLine);
			break;
		}

		boolean ok = genericLiteral(lines, "\"'", true, '\\', false, false);
		if (ok)
		{
			if (prefixLen == 1)
				_txt = pre1 + _txt;
			else if (prefixLen == 2)
				_txt = pre1 + pre2 + _txt;
			else if (prefixLen == 3) _txt = pre1 + pre2 + pre3 + _txt;
		}
		_currentChar -= prefixLen;
		return ok;
	}
}
