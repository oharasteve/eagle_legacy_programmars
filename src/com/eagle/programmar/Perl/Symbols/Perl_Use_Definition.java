// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 24, 2020

package com.eagle.programmar.Perl.Symbols;

public class Perl_Use_Definition extends Perl_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.USES;
	}
}
