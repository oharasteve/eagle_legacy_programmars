// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Django_Literal extends TerminalLiteralToken
{
	public Django_Literal()
	{
		super("'\"", true, '\\', false, false);
	}
}