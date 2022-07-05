// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Symbols;


public class Gupta_Function_Definition extends Gupta_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}
