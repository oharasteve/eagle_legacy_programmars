// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Terminals;

import com.eagle.tokens.terminals.TerminalOctalNumberToken;

public class Perl_OctalNumber extends TerminalOctalNumberToken
{
	public Perl_OctalNumber()
	{
		super("0", null, false);
	}
}
