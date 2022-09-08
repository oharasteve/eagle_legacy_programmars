// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class Ruby_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Ruby_Punctuation()
	{
		this('\0');
	}

	public Ruby_Punctuation(char punct)
	{
		super(punct);
	}

	public Ruby_Punctuation(String punct)
	{
		super(punct);
	}
}
