// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Julia_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Julia_KeywordChoice()
	{
		super();
	}
	
	public Julia_KeywordChoice(String... words)
	{
		super(words);
	}
}
