// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class AWK_Pattern extends TerminalLiteralToken
{
	public AWK_Pattern()
	{
		super("/", true, '\\', false, false);
	}
}
