// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Fortran_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Fortran_Keyword()
	{
		this("");
	}

	public Fortran_Keyword(String word)
	{
		super(word);
	}
}
