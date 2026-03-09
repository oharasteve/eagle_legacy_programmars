// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Algol68_Literal extends TerminalLiteralToken
{
	public Algol68_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
