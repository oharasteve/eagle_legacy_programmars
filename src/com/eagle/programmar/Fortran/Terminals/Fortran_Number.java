// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class Fortran_Number extends TerminalNumberToken
{
	public Fortran_Number()
	{
		super("Ee", "LlFfDd", true, false, '?');
	}
}
