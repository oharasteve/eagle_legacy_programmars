// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Powershell_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Powershell_KeywordChoice()
	{
		super();
	}
	
	public Powershell_KeywordChoice(String... words)
	{
		super(words);
	}
}
