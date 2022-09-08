// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Rust.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Rust_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Rust_KeywordChoice()
	{
		super();
	}
	
	public Rust_KeywordChoice(String... words)
	{
		super(words);
	}
}
