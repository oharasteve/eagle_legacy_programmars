// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Symbols;

public class FSharp_Function_Definition extends FSharp_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}
