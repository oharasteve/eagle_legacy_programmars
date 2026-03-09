// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Ruby_Number extends TerminalNumberToken
{
	public Ruby_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
