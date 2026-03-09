// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class MSSolution_Literal extends TerminalLiteralToken
{
	public MSSolution_Literal()
	{
		super("\"", false, ' ', false, false);
	}
}
