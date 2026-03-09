// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class CMacro_HexNumber extends TerminalHexNumberToken
{
	public CMacro_HexNumber()
	{
		super("0x", "UL", false);
	}
}
