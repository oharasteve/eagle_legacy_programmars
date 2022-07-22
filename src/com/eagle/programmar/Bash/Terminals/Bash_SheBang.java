// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public class Bash_SheBang extends Bash_Punctuation
{
	public Bash_SheBang()
	{
		super("#!");
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);

		// Check for shebang (#!) on line 1, columns 1-2
		if (_currentLine > 0 || _currentChar > 0) return false;
		if (rec.length() < 2) return false;
		if (rec.charAt(0) != '#' || rec.charAt(1) != '!') return false;
		
		// Yep, it is a she-bang
		foundIt(_currentLine, _currentChar + 1);
		return true;
	}

}
