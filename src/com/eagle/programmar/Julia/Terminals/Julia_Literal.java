// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Julia_Literal extends TerminalLiteralToken
{
	public Julia_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
