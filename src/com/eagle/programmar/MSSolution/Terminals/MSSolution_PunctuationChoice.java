// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class MSSolution_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public MSSolution_PunctuationChoice()
	{
		super();
	}
	
	public MSSolution_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
