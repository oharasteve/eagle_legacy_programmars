// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Symbols;

public class Ada_Variable_Definition extends Ada_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}
