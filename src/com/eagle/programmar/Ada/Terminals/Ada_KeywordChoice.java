// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Ada_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Ada_KeywordChoice()
	{
		super();
	}
	
	public Ada_KeywordChoice(String... words)
	{
		super(words);
	}
}
