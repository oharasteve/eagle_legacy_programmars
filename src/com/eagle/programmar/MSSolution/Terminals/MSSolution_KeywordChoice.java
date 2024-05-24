// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class MSSolution_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public MSSolution_KeywordChoice()
	{
		super();
	}

	public MSSolution_KeywordChoice(String... words)
	{
		super(words);
	}
}
