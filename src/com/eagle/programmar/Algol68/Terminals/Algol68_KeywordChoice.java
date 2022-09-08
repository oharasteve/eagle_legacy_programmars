// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Algol68_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Algol68_KeywordChoice()
	{
		super();
	}
	
	public Algol68_KeywordChoice(String... words)
	{
		super(words);
	}
}
