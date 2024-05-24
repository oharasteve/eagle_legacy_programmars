// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Terminals;

import com.eagle.tokens.terminals.TerminalPunctuationChoice;

public class Scala_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Scala_PunctuationChoice()
	{
		super();
	}

	public Scala_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}