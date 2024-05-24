// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class CMacro_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public CMacro_Punctuation()
	{
		this('\0');
	}

	public CMacro_Punctuation(char punct)
	{
		super(punct);
	}

	public CMacro_Punctuation(String punct)
	{
		super(punct);
	}
}
