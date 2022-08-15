// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2022

package com.eagle.programmar.Javascript.Symbols;

public class Javascript_Class_Definition extends Javascript_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.CLASS;
	}
}
