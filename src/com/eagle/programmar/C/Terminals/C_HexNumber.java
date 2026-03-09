// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class C_HexNumber extends TerminalHexNumberToken
{
	public C_HexNumber()
	{
		super("0x", "Ll", false);
	}
}
