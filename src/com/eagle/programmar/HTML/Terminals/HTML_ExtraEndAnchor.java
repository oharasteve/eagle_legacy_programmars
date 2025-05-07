// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 11, 2022

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.HTML.HTML_Anchor;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class HTML_ExtraEndAnchor extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 3 >= recLen) return false;
		char ch3 = rec.charAt(_currentChar + 2);

		if (rec.charAt(_currentChar) == '<' && rec.charAt(_currentChar + 1) == '/' && (ch3 == 'a' || ch3 == 'A')
				&& rec.charAt(_currentChar + 3) == '>')
		{
			// Have to fail if we are already inside an <a>
			AbstractToken parent = getParent();
			while (parent != null)
			{
				if (parent instanceof HTML_Anchor) return false;
				parent = parent.getParent();
			}

			_endLine = _currentLine;
			_txt = rec.substring(_currentChar, _currentChar + 4);
			foundIt(_endLine, _currentChar + 4);
			return true;
		}
		return false;
	}
	
	@Override
	public String description()
	{
		return "html extra end anchor";
	}
}
