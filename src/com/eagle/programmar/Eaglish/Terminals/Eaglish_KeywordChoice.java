// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Eaglish_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Eaglish_KeywordChoice()
	{
		super();
	}
	
	public Eaglish_KeywordChoice(String... words)
	{
		super(words);
	}
}
