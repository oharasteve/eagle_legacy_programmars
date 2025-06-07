// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class RPGFree_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public RPGFree_Keyword()
	{
		this("");
	}

	public RPGFree_Keyword(String word)
	{
		super(word);
	}
}
