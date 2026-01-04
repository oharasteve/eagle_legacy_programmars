// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2026

package com.eagle.programmar.Ada.Symbols;

public class Ada_Label_Definition extends Ada_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.LABEL;
	}
}
