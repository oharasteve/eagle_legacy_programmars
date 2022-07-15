// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalPunctuationChoice;
import com.eagle.tokens.TerminalPunctuationToken;

public class Powershell_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Powershell_PunctuationChoice()
	{
		super();
	}
	
	public Powershell_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
	
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! super.parse(lines)) return false;
		TerminalPunctuationToken token = _puncts[_which];
		if (token.isChar('-'))
		{
			// Make sure next char is NOT a letter. Such as -lt -or -match etc.
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar+1 < recLen)
			{
				char ch = rec.charAt(_currentChar + 1);
				if (Character.isAlphabetic(ch)) return false;
			}
		}
		return true;
	}
}