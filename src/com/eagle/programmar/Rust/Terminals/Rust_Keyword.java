// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordToken;

public class Rust_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Rust_Keyword()
	{
		this("");
	}

	public Rust_Keyword(String word)
	{
		super(word);
	}
}
