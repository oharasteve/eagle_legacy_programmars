// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Symbols;

public class Python_Variable_Definition extends Python_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
}
