// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Algol68_Number extends TerminalNumberToken
{
	public Algol68_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
