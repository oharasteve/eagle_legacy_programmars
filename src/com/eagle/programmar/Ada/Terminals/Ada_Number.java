// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Ada_Number extends TerminalNumberToken
{
	public Ada_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
