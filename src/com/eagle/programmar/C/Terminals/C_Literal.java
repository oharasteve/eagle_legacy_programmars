// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class C_Literal extends TerminalLiteralToken
{
	public C_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
