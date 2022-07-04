// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Go_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Go_Keyword()
	{
		this("");
	}

	public Go_Keyword(String word)
	{
		super(word);
	}
}
