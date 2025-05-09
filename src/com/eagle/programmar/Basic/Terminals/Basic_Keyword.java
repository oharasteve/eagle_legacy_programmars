// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Basic_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Basic_Keyword()
	{
		this("");
	}

	public Basic_Keyword(String word)
	{
		super(word);
	}
}
