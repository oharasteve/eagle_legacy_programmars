// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class C_Number extends TerminalNumberToken
{
	public C_Number()
	{
		super("Ee", "LlFfUu", true, true, '\'');
	}
}
