// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Scala_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Scala_Keyword()
	{
		this("");
	}

	public Scala_Keyword(String word)
	{
		super(word);
	}
}
