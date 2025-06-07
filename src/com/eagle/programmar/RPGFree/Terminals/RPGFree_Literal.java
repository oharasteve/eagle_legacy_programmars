// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class RPGFree_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'", false, '?', true, false);
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("'", false, '?', true, false);
	}
}
