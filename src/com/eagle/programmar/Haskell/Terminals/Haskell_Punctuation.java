// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Haskell_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Haskell_Punctuation()
	{
		this('\0');
	}

	public Haskell_Punctuation(char punct)
	{
		super(punct);
	}

	public Haskell_Punctuation(String punct)
	{
		super(punct);
	}
}
