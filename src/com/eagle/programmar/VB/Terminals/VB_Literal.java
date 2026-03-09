// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class VB_Literal extends TerminalLiteralToken
{
	public VB_Literal()
	{
		super("\"", false, '?', true, false);
	}
}
