// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Fortran_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Fortran_Punctuation()
	{
		this('\0');
	}

	public Fortran_Punctuation(char punct)
	{
		super(punct);
	}

	public Fortran_Punctuation(String punct)
	{
		super(punct);
	}
}
