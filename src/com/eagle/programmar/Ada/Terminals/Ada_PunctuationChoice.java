// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Ada_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Ada_PunctuationChoice()
	{
		super();
	}
	
	public Ada_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}