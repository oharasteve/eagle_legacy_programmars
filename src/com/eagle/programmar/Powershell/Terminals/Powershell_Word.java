// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2024

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalWord;

public class Powershell_Word extends TerminalWord
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericWord(lines, ",;(){}[]|");
	}
}
