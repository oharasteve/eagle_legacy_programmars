// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class HTML_Literal extends TerminalLiteralToken
{
	public HTML_Literal()
	{
		super("'\"", false, '?', false, true);
	}
}
