// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Rexx_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Rexx_Keyword()
	{
		this("");
	}

	public Rexx_Keyword(String word)
	{
		super(word);
	}
}
