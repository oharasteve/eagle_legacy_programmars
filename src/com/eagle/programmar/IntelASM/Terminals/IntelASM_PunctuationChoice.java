// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2022

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class IntelASM_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public IntelASM_PunctuationChoice()
	{
		super();
	}
	
	public IntelASM_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}