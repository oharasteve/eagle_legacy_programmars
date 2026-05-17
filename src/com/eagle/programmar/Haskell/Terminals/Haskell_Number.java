// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Haskell_Number extends TerminalNumberToken
{
	public Haskell_Number()
	{
		super("Ee", null, true, false, '?');
	}
}
