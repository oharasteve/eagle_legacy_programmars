// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Symbols;

import com.eagle.programmar.Powershell.Terminals.Powershell_Identifier;
import com.eagle.tokens.DefinitionInterface;

public abstract class Powershell_Identifier_Definition extends Powershell_Identifier implements DefinitionInterface
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}