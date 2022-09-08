// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2020

package com.eagle.programmar.BNF.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class BNF_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public BNF_Keyword()
	{
		this(null);
	}

	public BNF_Keyword(String word)
	{
		super(word);
	}
}
