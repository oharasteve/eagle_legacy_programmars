// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Rexx_Literal extends TerminalLiteralToken
{
	public Rexx_Literal()
	{
		super("\"", false, '?', true, false);
	}
}
