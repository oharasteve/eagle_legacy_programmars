// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Symbols;

public class IntelASM_Label_Definition extends IntelASM_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.LABEL;
	}
}