// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Go_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Go_Punctuation()
	{
		this('\0');
	}

	public Go_Punctuation(char punct)
	{
		super(punct);
	}

	public Go_Punctuation(String punct)
	{
		super(punct);
	}
}
