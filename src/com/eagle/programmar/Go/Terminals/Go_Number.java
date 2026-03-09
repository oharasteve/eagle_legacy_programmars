// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Go_Number extends TerminalNumberToken
{
	public Go_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
