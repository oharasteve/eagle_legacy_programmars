// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class FSharp_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public FSharp_Keyword()
	{
		this("");
	}

	public FSharp_Keyword(String word)
	{
		super(word);
	}
}
