// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Haskell_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Haskell_KeywordChoice()
	{
		super();
	}

	public Haskell_KeywordChoice(String... words)
	{
		super(words);
	}
}
