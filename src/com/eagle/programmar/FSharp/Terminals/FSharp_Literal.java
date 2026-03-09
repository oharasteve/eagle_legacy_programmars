// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class FSharp_Literal extends TerminalLiteralToken
{
	public FSharp_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
