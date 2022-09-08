// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class CMacro_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public CMacro_PunctuationChoice()
	{
		super();
	}
	
	public CMacro_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}