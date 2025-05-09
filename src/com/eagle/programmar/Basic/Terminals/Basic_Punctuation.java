// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Basic_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Basic_Punctuation()
	{
		this('\0');
	}

	public Basic_Punctuation(char punct)
	{
		super(punct);
	}

	public Basic_Punctuation(String punct)
	{
		super(punct);
	}
}
