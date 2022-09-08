// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Powershell_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Powershell_Keyword()
	{
		this("");
	}

	public Powershell_Keyword(String word)
	{
		super(word);
	}
}
