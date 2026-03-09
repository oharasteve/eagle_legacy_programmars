// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class JSON_Literal extends TerminalLiteralToken
{
	public JSON_Literal()
	{
		super("\"", true, '\\', false, true);
	}
}
