// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Symbols;

public class Haskell_Data_Definition extends Haskell_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}