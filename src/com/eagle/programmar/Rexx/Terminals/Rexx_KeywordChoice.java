// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Rexx_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Rexx_KeywordChoice()
	{
		super();
	}

	public Rexx_KeywordChoice(String... words)
	{
		super(words);
	}
}
