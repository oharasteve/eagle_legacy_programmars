// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Julia_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Julia_Keyword()
	{
		this("");
	}

	public Julia_Keyword(String word)
	{
		super(word);
	}
}
