// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Rust_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "Ll", true);
	}

	public Rust_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}
