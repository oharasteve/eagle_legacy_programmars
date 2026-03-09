// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class TCL_Number extends TerminalNumberToken
{
	public TCL_Number()
	{
		super("Ee", "Ll", true, false, '?');
	}
}
