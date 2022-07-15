// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Bash_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Bash_Punctuation()
	{
		this('\0');
	}

	public Bash_Punctuation(char punct)
	{
		super(punct);
	}
	
	public Bash_Punctuation(String punct)
	{
		super(punct);
	}
}
