// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Basic_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Basic_PunctuationChoice()
	{
		super();
	}

	public Basic_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}