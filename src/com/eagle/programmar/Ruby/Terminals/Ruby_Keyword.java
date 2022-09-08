// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Ruby_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Ruby_Keyword()
	{
		this("");
	}

	public Ruby_Keyword(String word)
	{
		super(word);
	}
}
