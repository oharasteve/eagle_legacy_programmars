// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Ada_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Ada_Keyword()
	{
		this("");
	}

	public Ada_Keyword(String word)
	{
		super(word);
	}
}
