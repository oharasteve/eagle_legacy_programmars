// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class TCL_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public TCL_KeywordChoice()
	{
		super();
	}
	
	public TCL_KeywordChoice(String... words)
	{
		super(words);
	}
}
