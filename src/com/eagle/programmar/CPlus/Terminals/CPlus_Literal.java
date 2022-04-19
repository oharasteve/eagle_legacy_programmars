// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 19, 2022

package com.eagle.programmar.CPlus.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CPlus_Literal extends TerminalLiteralToken
{
	private static final String PREFIXES = "uLR";
	
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
		
		int prefixLen = 0;
		if (PREFIXES.indexOf(pre1) >= 0)
		{
			prefixLen++;
			// Special case of u8
			if (pre1 == 'u' && pre2 == '8')
			{
				prefixLen++;
			}
		}
		_currentChar += prefixLen;

		boolean ok = genericLiteral(lines, "\"'", true, '\\', false, false);
		if (ok)
		{
			if (prefixLen == 1) _txt = pre1 + _txt;
			else if (prefixLen == 2) _txt = pre1 + pre2 + _txt;
		}
		_currentChar -= prefixLen;
		return ok;
	}
}
