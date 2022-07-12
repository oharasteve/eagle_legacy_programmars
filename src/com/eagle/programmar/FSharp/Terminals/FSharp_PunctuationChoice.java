// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class FSharp_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public FSharp_PunctuationChoice()
	{
		super();
	}
	
	public FSharp_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}