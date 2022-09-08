// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Algol68_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Algol68_Punctuation()
	{
		this('\0');
	}

	public Algol68_Punctuation(char punct)
	{
		super(punct);
	}

	public Algol68_Punctuation(String punct)
	{
		super(punct);
	}
}
