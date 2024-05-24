// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class AWK_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public AWK_PunctuationChoice()
	{
		super();
	}

	public AWK_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}