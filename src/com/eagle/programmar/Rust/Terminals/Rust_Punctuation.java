// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Rust_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Rust_Punctuation()
	{
		this('\0');
	}

	public Rust_Punctuation(char punct)
	{
		super(punct);
	}

	public Rust_Punctuation(String punct)
	{
		super(punct);
	}
}
