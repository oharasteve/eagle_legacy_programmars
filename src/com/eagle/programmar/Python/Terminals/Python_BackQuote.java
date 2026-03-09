// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 26, 2014

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Python_BackQuote extends TerminalLiteralToken
{
	public Python_BackQuote()
	{
		super("`", false, '?', false, false);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();

		// Pick up the prefix(es), if they are present
		char pre1 = ' ';
		char pre2 = ' ';
		if (_currentChar < nc) pre1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) pre2 = rec.charAt(_currentChar + 1);

		int prefixLen;
		if (pre1 == 'u' && pre2 == 'r')
			prefixLen = 2;
		else if (pre1 == 'r' && pre2 == 'u')
			prefixLen = 2;
		else if (pre1 == 'b' && pre2 == 'r')
			prefixLen = 2;
		else if (pre1 == 'r')
			prefixLen = 1;
		else if (pre1 == 'u')
			prefixLen = 1;
		else if (pre1 == 'b')
			prefixLen = 1;
		else
			prefixLen = 0;
		_currentChar += prefixLen;

		boolean ok = false;
		if (_currentChar < nc && rec.charAt(_currentChar) == '`')
		{
			ok = super.parse(lines);
			if (ok)
			{
				switch (prefixLen)
				{
				case 1:
					_txt = pre1 + _txt;
					break;
				case 2:
					_txt = pre1 + pre2 + _txt;
					break;
				}
			}
		}

		_currentChar -= prefixLen;
		return ok;
	}

	@Override
	public String description()
	{
		return "back quote literal";
	}
}
