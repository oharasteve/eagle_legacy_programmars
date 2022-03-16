package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class JSON_Punctuation  extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public JSON_Punctuation()
	{
		this('\0');
	}

	public JSON_Punctuation(char punct)
	{
		super(punct);
	}

	public JSON_Punctuation(String punct)
	{
		super(punct);
	}
}
