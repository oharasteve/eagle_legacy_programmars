// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public class Basic_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS, null, true, false);
	}
}
