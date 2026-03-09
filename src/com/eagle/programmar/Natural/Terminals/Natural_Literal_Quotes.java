// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Natural_Literal_Quotes extends TerminalLiteralToken
{
	public Natural_Literal_Quotes()
	{
		super("'", false, '?', false, false);
	}
}