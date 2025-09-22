// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 21, 2025

package com.eagle.programmar.AWK.Symbols;

public class AWK_Parameter_Definition extends AWK_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.PARAMETER;
	}
}
