// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Symbols;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.DefinitionInterface;
import com.eagle.tokens.TerminalIdentifierToken;

public class Powershell_Function_Definition extends TerminalIdentifierToken implements DefinitionInterface
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS, ALPHAS+DIGITS+"_", true);
	}

	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}
