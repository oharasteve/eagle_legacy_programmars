// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Perl_HexNumber extends TerminalHexNumberToken
{
	public Perl_HexNumber()
	{
		super("0x", null, false);
	}
}
