// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Javascript_Number extends TerminalNumberToken
{
	public Javascript_Number()
	{
		super("eE", "n", true, true, '_'); // the underscore is ignorable: 60_000 (Oct 2026, shane branch)
	}
}
