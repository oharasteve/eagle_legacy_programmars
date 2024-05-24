// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class FSharp_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public FSharp_KeywordChoice()
	{
		super();
	}

	public FSharp_KeywordChoice(String... words)
	{
		super(words);
	}
}
