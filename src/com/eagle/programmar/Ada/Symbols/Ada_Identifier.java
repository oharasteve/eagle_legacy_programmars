// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Symbols;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public class Ada_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_", false);
	}
}