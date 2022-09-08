// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Julia_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Julia_Punctuation()
	{
		this('\0');
	}

	public Julia_Punctuation(char punct)
	{
		super(punct);
	}

	public Julia_Punctuation(String punct)
	{
		super(punct);
	}
}
