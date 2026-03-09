// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Scala_Number extends TerminalNumberToken
{
	public Scala_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
