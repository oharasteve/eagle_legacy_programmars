// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Go_Literal extends TerminalLiteralToken
{
	public Go_Literal()
	{
		super("`\"", true, '\\', false, true);
	}
}
