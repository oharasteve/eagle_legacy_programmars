// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Julia_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Julia_PunctuationChoice()
	{
		super();
	}

	public Julia_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}