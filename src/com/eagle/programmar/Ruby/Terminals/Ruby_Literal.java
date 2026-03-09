// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Ruby_Literal extends TerminalLiteralToken
{
	public Ruby_Literal()
	{
		super("\"'", true, '\\', false, false);
	}
}
