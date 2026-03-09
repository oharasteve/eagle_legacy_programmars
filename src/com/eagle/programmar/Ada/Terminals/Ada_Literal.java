// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Ada_Literal extends TerminalLiteralToken
{
	public Ada_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}