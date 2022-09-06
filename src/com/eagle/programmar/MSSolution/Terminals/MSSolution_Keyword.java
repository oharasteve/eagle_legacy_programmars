// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class MSSolution_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public MSSolution_Keyword()
	{
		this("");
	}

	public MSSolution_Keyword(String word)
	{
		super(word);
	}
}
