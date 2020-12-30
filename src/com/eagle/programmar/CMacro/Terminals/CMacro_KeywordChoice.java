// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class CMacro_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public CMacro_KeywordChoice()
	{
		super();
	}
	
	public CMacro_KeywordChoice(String... words)
	{
		super(words);
	}
}
