// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class IntelASM_Literal extends TerminalLiteralToken
{
	public IntelASM_Literal()
	{
		super("'\"", false, '?', false, false);
	}
}
