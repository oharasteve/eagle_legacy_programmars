// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2014

package com.eagle.programmar.Java.Symbols;

public class Java_Label_Definition extends Java_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.LABEL;
	}
}
