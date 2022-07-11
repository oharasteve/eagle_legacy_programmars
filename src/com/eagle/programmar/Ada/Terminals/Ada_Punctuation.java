// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Ada_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Ada_Punctuation()
	{
		this('\0');
	}

	public Ada_Punctuation(char punct)
	{
		super(punct);
	}

	public Ada_Punctuation(String punct)
	{
		super(punct);
	}
}
