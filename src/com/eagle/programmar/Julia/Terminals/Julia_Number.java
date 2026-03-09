// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Julia_Number extends TerminalNumberToken
{
	public Julia_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
