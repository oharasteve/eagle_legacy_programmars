// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2022

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class IntelASM_HexNumber extends TerminalHexNumberToken
{
	public IntelASM_HexNumber()
	{
		super("0x", null, false);
	}
}
