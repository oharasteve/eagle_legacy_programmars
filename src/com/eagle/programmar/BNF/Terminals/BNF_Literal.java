// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class BNF_Literal extends TerminalLiteralToken
{
	public BNF_Literal()
	{
		super("'\"", true, '\\', false, false);
	}
}
