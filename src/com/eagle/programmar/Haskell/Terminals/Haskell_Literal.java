// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Haskell_Literal extends TerminalLiteralToken
{
	public Haskell_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
