// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class RPGFree_Literal extends TerminalLiteralToken
{
	public RPGFree_Literal()
	{
		super("'", false, '?', true, false);
	}
}
