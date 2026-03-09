// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class C_Character_Literal extends TerminalLiteralToken
{
	public C_Character_Literal()
	{
		super("'", true, '\\', false, false);
	}
}