// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalPunctuationChoice;

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
		if (!super.parse(lines)) return false;
		return super.dontAllowLettersAfterHyphen(lines);
	}
}