// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Eaglish_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Eaglish_Punctuation()
	{
		this('\0');
	}

	public Eaglish_Punctuation(char punct)
	{
		super(punct);
	}

	public Eaglish_Punctuation(String punct)
	{
		super(punct);
	}
}
