// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class RPGFree_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public RPGFree_KeywordChoice()
	{
		super();
	}

	public RPGFree_KeywordChoice(String... words)
	{
		super(words);
	}
}
