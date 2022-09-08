// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Bash_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Bash_PunctuationChoice()
	{
		super();
	}
	
	public Bash_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! super.parse(lines)) return false;
		return super.dontAllowLettersAfterHyphen(lines);
	}
}