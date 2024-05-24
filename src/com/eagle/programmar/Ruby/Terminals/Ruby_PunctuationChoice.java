// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Ruby_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Ruby_PunctuationChoice()
	{
		super();
	}

	public Ruby_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}