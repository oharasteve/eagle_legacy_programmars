// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Javascript_HexNumber extends TerminalHexNumberToken
{
	public Javascript_HexNumber()
	{
		super("0x", "Ll", false);
	}
}
