// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Rust_Number extends TerminalNumberToken
{
	public Rust_Number()
	{
		super("Ee", "LlFfDd", true, true, '_');
	}

	public static Rust_Number generateNumber(String value, AbstractToken source)
	{
		Rust_Number num = new Rust_Number();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}
}
