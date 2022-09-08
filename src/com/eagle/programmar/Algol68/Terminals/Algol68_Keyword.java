// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Algol68_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Algol68_Keyword()
	{
		this("");
	}

	public Algol68_Keyword(String word)
	{
		super(word);
	}
}
