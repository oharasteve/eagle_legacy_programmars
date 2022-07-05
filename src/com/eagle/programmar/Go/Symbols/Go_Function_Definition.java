// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Symbols;

public class Go_Function_Definition extends Go_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}
