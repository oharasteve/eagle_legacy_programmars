// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Symbols;

public class Rexx_Variable_Definition extends Rexx_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}
