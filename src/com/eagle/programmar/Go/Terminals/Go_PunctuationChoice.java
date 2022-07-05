// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Go_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Go_PunctuationChoice()
	{
		super();
	}
	
	public Go_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}