// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Ruby_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Ruby_KeywordChoice()
	{
		super();
	}
	
	public Ruby_KeywordChoice(String... words)
	{
		super(words);
	}
}
