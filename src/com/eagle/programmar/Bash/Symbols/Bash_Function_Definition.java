// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Symbols;

public class Bash_Function_Definition extends Bash_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}
}