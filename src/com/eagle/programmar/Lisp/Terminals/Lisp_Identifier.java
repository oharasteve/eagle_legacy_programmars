// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalIdentifierToken;

public abstract class Lisp_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar + 1 < nc)
		{
			if (rec.charAt(_currentChar) == '|')
			{
				int secondBar = rec.indexOf('|', _currentChar + 1);
				if (secondBar > _currentChar + 1)
				{
					_id = rec.substring(_currentChar, secondBar + 1);
					foundIt(_currentLine, secondBar);
					return true;
				}
			}
		}
		
		return genericIdentifier(lines, ALPHAS+"-*", ALPHAS+DIGITS+"->*.!$", true);
	}
}
