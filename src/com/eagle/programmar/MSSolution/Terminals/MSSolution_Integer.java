// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.tokens.terminals.TerminalIntegerToken;

public class MSSolution_Integer extends TerminalIntegerToken
{
	@Override
	public String showString()
	{
		return "MS Solution integer";
	}

	@Override
	public String description()
	{
		return "MS Solution integer";
	}
}