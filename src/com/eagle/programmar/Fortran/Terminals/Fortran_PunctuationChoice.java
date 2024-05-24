// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Fortran_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Fortran_PunctuationChoice()
	{
		super();
	}

	public Fortran_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}