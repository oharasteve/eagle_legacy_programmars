// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Basic_Literal extends TerminalLiteralToken
{
	public Basic_Literal()
	{
		super("\"", false, '?', true, false);
	}
}
