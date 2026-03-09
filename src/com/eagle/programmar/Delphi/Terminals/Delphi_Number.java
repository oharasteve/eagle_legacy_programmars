// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Delphi_Number extends TerminalNumberToken
{
	public Delphi_Number()
	{
		super("Ee", null, true, false, '?');
	}
}
