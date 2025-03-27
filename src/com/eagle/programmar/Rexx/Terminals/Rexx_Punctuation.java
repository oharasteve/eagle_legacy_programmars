// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Rexx_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Rexx_Punctuation()
	{
		this('\0');
	}

	public Rexx_Punctuation(char punct)
	{
		super(punct);
	}

	public Rexx_Punctuation(String punct)
	{
		super(punct);
	}
}
