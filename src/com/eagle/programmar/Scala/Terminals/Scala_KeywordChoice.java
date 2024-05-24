// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Terminals;

import com.eagle.tokens.terminals.TerminalKeywordChoice;

public class Scala_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Scala_KeywordChoice()
	{
		super();
	}

	public Scala_KeywordChoice(String... words)
	{
		super(words);
	}
}
