// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class CMacro_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public CMacro_Keyword()
	{
		this(null);
	}

	public CMacro_Keyword(String word)
	{
		super(word);
	}
}
