// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Haskell_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Haskell_Keyword()
	{
		this("");
	}

	public Haskell_Keyword(String word)
	{
		super(word);
	}
}
