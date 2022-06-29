// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Bash_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Bash_Keyword()
	{
		this("");
	}

	public Bash_Keyword(String word)
	{
		super(word);
	}
}
