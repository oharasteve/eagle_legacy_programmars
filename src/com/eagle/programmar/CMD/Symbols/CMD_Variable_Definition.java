// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Symbols;

public class CMD_Variable_Definition extends CMD_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}
