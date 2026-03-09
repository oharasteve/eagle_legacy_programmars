// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2014

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.terminals.TerminalOctalNumberToken;

public class Python_OctalNumber extends TerminalOctalNumberToken
{
	public Python_OctalNumber()
	{
		super("0o", "Ll", false);
	}
}
