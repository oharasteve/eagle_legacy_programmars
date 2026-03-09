// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Powershell_Literal extends TerminalLiteralToken
{
	public Powershell_Literal()
	{
		super("\"'", true, '`', true, false);	// Careful with the funny backtick
	}
}
