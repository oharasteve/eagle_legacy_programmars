// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 15, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public class Eaglish_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "_", true, false);
	}
}
