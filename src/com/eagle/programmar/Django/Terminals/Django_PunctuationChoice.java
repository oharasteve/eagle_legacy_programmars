// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2022

package com.eagle.programmar.Django.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Django_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Django_PunctuationChoice()
	{
		super();
	}
	
	public Django_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}