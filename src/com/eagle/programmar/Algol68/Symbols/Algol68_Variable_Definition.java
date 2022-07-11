// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Symbols;

public class Algol68_Variable_Definition extends Algol68_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}
