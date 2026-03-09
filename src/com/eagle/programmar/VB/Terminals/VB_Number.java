// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class VB_Number extends TerminalNumberToken
{
	public VB_Number()
	{
		super("Ee", null, true, false, '?');
	}
}
