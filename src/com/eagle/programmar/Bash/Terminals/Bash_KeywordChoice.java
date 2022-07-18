// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Bash_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Bash_KeywordChoice()
	{
		super();
	}
	
	public Bash_KeywordChoice(String... words)
	{
		super(words);
	}
}
