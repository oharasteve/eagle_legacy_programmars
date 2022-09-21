// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Symbols;

import com.eagle.tokens.DefinitionInterface;

public class Powershell_Function_Definition extends Powershell_Identifier_Definition implements DefinitionInterface
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}
