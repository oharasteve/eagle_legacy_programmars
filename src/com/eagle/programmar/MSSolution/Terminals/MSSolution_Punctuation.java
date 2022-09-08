// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationToken;

public class MSSolution_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public MSSolution_Punctuation()
	{
		this('\0');
	}

	public MSSolution_Punctuation(char punct)
	{
		super(punct);
	}

	public MSSolution_Punctuation(String punct)
	{
		super(punct);
	}
}
