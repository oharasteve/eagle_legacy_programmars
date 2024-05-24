// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Eaglish_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Eaglish_PunctuationChoice()
	{
		super();
	}

	public Eaglish_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}