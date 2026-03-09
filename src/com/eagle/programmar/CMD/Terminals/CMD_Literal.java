// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CMD_Literal extends TerminalLiteralToken
{
	public CMD_Literal()
	{
		super("'\"", false, '?', false, false);
	}
}
