// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 15, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Eaglish_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Eaglish_Keyword()
	{
		this("");
	}

	public Eaglish_Keyword(String word)
	{
		super(word);
	}
}
