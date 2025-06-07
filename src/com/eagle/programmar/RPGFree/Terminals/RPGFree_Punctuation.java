// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class RPGFree_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public RPGFree_Punctuation()
	{
		this('\0');
	}

	public RPGFree_Punctuation(char punct)
	{
		super(punct);
	}

	public RPGFree_Punctuation(String punct)
	{
		super(punct);
	}
}
