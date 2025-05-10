// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Symbols;

import com.eagle.programmar.Basic.Terminals.Basic_Identifier;
import com.eagle.tokens.DefinitionInterface;

public class Basic_Identifier_Definition extends Basic_Identifier implements DefinitionInterface
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}