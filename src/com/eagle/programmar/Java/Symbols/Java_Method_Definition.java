// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.programmar.Java.Symbols;

public class Java_Method_Definition extends Java_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.METHOD;
	}
}
