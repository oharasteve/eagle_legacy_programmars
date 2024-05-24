// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Go_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Go_KeywordChoice()
	{
		super();
	}

	public Go_KeywordChoice(String... words)
	{
		super(words);
	}
}
