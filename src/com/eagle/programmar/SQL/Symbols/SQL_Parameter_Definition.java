// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 3025

package com.eagle.programmar.SQL.Symbols;

public class SQL_Parameter_Definition extends SQL_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.PARAMETER;
	}
}