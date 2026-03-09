// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Delphi_HexNumber extends TerminalHexNumberToken
{
	public Delphi_HexNumber()
	{
		super("$", null, false);
	}
}