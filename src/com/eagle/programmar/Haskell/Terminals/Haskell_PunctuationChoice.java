// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Haskell_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Haskell_PunctuationChoice()
	{
		super();
	}

	public Haskell_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}