// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class FSharp_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public FSharp_Punctuation()
	{
		this('\0');
	}

	public FSharp_Punctuation(char punct)
	{
		super(punct);
	}
	
	public FSharp_Punctuation(String punct)
	{
		super(punct);
	}
}
