// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Rust_Character_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return super.genericDescription("'", true, '\\', false, false);
	}

	public Rust_Character_Literal generateCharLiteral(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}