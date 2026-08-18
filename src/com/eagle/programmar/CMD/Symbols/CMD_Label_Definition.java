// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Symbols;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public class CMD_Label_Definition extends CMD_Identifier_Definition
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		int pos = _currentChar - 1;
		if (!super.parse(lines)) return false;

		// The label must be the first thing on a line
		// Leading spaces are ok
		EagleLineReader rec = lines.get(_currentLine);
		if (pos > rec.length()) return false;	// Should not be possible, but ...
		for (int i = 0; i < pos; i++)
		{
			char ch = rec.charAt(i);
			if (ch != ' ' && ch != '\t') return false;
		}
		return true;
	}
	
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.LABEL;
	}
}
