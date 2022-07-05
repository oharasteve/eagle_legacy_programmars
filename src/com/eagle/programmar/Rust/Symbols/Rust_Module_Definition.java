// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust.Symbols;

public class Rust_Module_Definition extends Rust_Identifier_Definition
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.MODULE;
	}
}
