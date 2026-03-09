// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Fortran_Literal extends TerminalLiteralToken
{
	public Fortran_Literal()
	{
		super("'", false, ' ', true, false);
	}
}
