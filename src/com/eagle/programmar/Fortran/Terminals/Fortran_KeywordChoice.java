// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Fortran_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Fortran_KeywordChoice()
	{
		super();
	}
	
	public Fortran_KeywordChoice(String... words)
	{
		super(words);
	}
}
