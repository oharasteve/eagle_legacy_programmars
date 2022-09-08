// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 19, 2022

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class JSON_Punctuation  extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public JSON_Punctuation()
	{
		this('\0');
	}

	public JSON_Punctuation(char punct)
	{
		super(punct);
	}

	public JSON_Punctuation(String punct)
	{
		super(punct);
	}
}
