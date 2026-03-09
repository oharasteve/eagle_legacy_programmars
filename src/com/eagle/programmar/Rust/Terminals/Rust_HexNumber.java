// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Rust_HexNumber extends TerminalHexNumberToken
{
	public Rust_HexNumber()
	{
		super("0x", "Ll", true);
	}

	public static Rust_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		Rust_HexNumber num = new Rust_HexNumber();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}
}
